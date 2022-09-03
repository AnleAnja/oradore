//
//  RoomsView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

let rooms = DummyData.shared.ProgramEntriesPreview().map {
    $0.room
}

struct RoomsView: View {
    
    @State private var searchText = ""
    
    private var searchResults: [Room] {
        if searchText.isEmpty {
            return rooms
        } else {
            return rooms.filter {
                $0.name.contains(searchText)
            }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(searchResults, id: \.self) { room in
                    NavigationLink(destination: Text(room.id)) {
                        VStack(alignment: .leading) {
                            Spacer()
                            Text(room.name)
                            Spacer()
                        }
                    }
                }
                .navigationTitle("Räume")
                .searchable(text: $searchText)
            }
        }
    }
}
