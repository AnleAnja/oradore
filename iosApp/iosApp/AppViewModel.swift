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
    @Published var favorites : [ProgramEntry] = []
    
    func isFavorite(entryId: String) -> Bool {
        return favorites.contains(where: { entry in entry.id == entryId })
    }
    
    private func fav(programEntry: ProgramEntry) {
        favorites.append(programEntry)
    }
    
    private func unFav(programEntry: ProgramEntry) {
        if let index = favorites.firstIndex(of: programEntry) {
            favorites.remove(at: index)
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
}
