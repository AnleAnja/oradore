//
//  AppViewModel.swift
//  iosApp
//
//  Created by Anja on 03.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared


class AppViewModel: ObservableObject, ViewModelObserver {

    @Published
    private(set) var favorites : [GroupProgramEntries] = []
    
    @Published
    private(set) var programEntries: [GroupProgramEntries] = []
    
    @Published
    private(set) var speakers: [Speaker] = []
    
    @Published
    private(set) var rooms: [Room] = []
    
    let viewModel: SharedViewModel
    
    init(api: NetworkApi, storage: FavoritesStorage) {
        self.viewModel = SharedViewModel(api: api, storage: storage)
        self.viewModel.registerObserver(observer: self)
    }
    
    deinit {
        self.viewModel.unregisterObserver(observer: self)
    }
    
    func updateFavorites(favorites: [GroupProgramEntries]) {
        self.favorites = favorites
    }
    
    func updateProgramEntries(entries: [GroupProgramEntries]) {
        self.programEntries = entries
    }
    
    func updateRooms(rooms: [Room]) {
        self.rooms = rooms
    }
    
    func updateSpeakers(speakers: [Speaker]) {
        self.speakers = speakers
    }
    
    func fetchProgramEntries() {
        viewModel.fetchProgramEntries()
    }
    
    func fetchSpeakers() {
        viewModel.fetchSpeakers()
    }
    
    func fetchRooms() {
        viewModel.fetchRooms()
    }
    
    func isFavorite(entryId: String) -> Bool {
        viewModel.isFavorite(programEntryId: entryId)
    }
    
    func toggleFav(programEntry: ProgramEntry) {
        viewModel.toggleFav(programEntryId: programEntry.id)
    }
    
    func getFavStateIcon(entryId: String) -> String {
        var iconName = ""
        if (isFavorite(entryId: entryId)) {
            iconName = "star.fill"
        } else {
            iconName = "star"
        }
        return iconName
    }
  
    func filterProgramEntries(`where` predicate: @escaping (ProgramEntry) -> Bool) -> [GroupProgramEntries] {
        viewModel.filterProgramEntries { predicate($0) ? true : false }
    }
}
