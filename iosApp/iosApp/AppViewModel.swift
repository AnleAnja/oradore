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


class AppViewModel : ObservableObject{
    @Published var favoriteProgramEntryIds : [String] = []
    
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
    
    func favorites() -> [ProgramEntryPreview] {
        DummyData.shared.ProgramEntriesPreview().filter { isFavorite(entryId: $0.id) }
    }
}
