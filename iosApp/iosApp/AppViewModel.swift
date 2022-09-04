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
    var favorites : [ProgramEntry] = []
    var iconName : String = "star"
    
    func isFavorite(programEntry: ProgramEntry) -> Bool {
        return favorites.contains(programEntry)
    }
    
    func fav(programEntry: ProgramEntry) {
        favorites.append(programEntry)
    }
    
    func unFav(programEntry: ProgramEntry) {
        if let index = favorites.firstIndex(of: programEntry) {
            favorites.remove(at: index)
        }
    }
    
    func toggleFav(programEntry: ProgramEntry) {
        if (isFavorite(programEntry: programEntry)) {
            unFav(programEntry: programEntry)
        } else {
            fav(programEntry: programEntry)
        }
    }
    
    func getFavStateIcon(programEntry: ProgramEntry) -> String {
        if (isFavorite(programEntry: programEntry)) {
            iconName = "star.fill"
        } else {
            iconName = "star"
        }
        return iconName
    }
}
