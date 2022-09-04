//
//  ProgramEntryDetailView.swift
//  iosApp
//
//  Created by Anja on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProgramEntryDetailView: View {
    let id: String
    var body: some View {
        let entry = getProgramEntryById()
        let room = getRoomById(id: entry.roomId)
        let formatColor = Color(hexStringToUIColor(hex: entry.format.hexColor))
        ScrollView {
            VStack (alignment: .leading) {
                HStack {
                    Text(entry.format.label)
                        .foregroundColor(formatColor)
                        .font(.caption)
                    Spacer()
                    Image(systemName: "star")
                        .foregroundColor(.yellow)
                }
                Text(entry.name).font(.title)
                HStack {
                    Image(systemName: "clock")
                    Text(timeFormatter(time: entry.timeRange.start) + " - " + timeFormatter(time: entry.timeRange.end) + " Uhr").font(.caption2)
                }
                .padding(.top)
                if let room = room {
                    HStack {
                        Image(systemName: "location")
                        Text(room.name).font(.caption2)
                    }
                    .padding(.bottom)
                }
                Text(entry.description_).font(.callout)
            }
            .padding(.leading)
            .padding(.trailing)
            
        }
    }
    
    func getProgramEntryById() -> ProgramEntry {
        return DummyData.shared.ProgramEntries()[DummyData.shared.ProgramEntries().firstIndex(where: {
            $0.id == id
        })!]
    }
    
    func getRoomById(id : String) -> Room? {
        return DummyData.shared.Rooms().first(where: {$0.id == id})
    }
}
