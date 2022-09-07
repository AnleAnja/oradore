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


class AppViewModel: ObservableObject {
  
    @Published
    private(set) var favoriteProgramEntryIds : [String] = []
    
    @Published
    private(set) var programEntries: [GroupProgramEntries] = []
    
    @Published
    private(set) var speakers: [Speaker] = []
    
    @Published
    private(set) var rooms: [Room] = []
    
    let api: NetworkApi
    
    init(api: NetworkApi) {
        self.api = api
    }
    
    func fetchProgramEntries() {
        api.fetchProgramEntries { entries, _ in
            if let entries = entries {
                self.programEntries = entries
            }
        }
    }
    
    func fetchSpeakers() {
        api.fetchSpeakers { speakers, _ in
            if let speakers = speakers {
                self.speakers = speakers
            }
        }
    }
    
    func fetchRooms() {
        api.fetchRooms { rooms, _ in
            if let rooms = rooms {
                self.rooms = rooms
            }
        }
    }
    
    func isFavorite(entryId: String) -> Bool {
        return favoriteProgramEntryIds.contains(entryId)
    }
    
    private func fav(programEntry: ProgramEntry) {
        favoriteProgramEntryIds.append(programEntry.id)
    }
    
    private func unFav(programEntry: ProgramEntry) {
        if let index = favoriteProgramEntryIds.firstIndex(of: programEntry.id) {
            favoriteProgramEntryIds.remove(at: index)
        }
    }
    
    func toggleFav(programEntry: ProgramEntry) {
        if (isFavorite(entryId: programEntry.id)) {
            unFav(programEntry: programEntry)
        } else {
            fav(programEntry: programEntry)
        }
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
  
    func filterProgramEntries(`where` predicate: (ProgramEntry) -> Bool) -> [GroupProgramEntries] {
        programEntries.reduce(into: []) { acc, group in
            let start = group.start
            let entries = group.entries
            let newEntries = entries.filter(predicate)
            if !newEntries.isEmpty {
                acc.append(GroupProgramEntries(start: start, entries: newEntries))
            }
        }
    }
    
    func favorites() -> [GroupProgramEntries] {
        filterProgramEntries(where: { isFavorite(entryId: $0.id) })
    }
}
