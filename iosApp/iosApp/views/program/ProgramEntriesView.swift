//
//  ProgramEntriesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared


struct ProgramEntriesView: View {
    @State private var searchText = ""
    
    let title: String
    let programEntries: [Int64: [ProgramEntry]]
    
    init(title: String, entries: [ProgramEntry]) {
        self.title = title
        self.programEntries = Dictionary(grouping: entries, by: { $0.timeRange.start })
    }
    
    private var searchResults: [Int64 : [ProgramEntry]] {
        if searchText.isEmpty {
            return programEntries
        } else {
            return programEntries.filter { key, value in
                value.contains { entry in
                    entry.name.lowercased().contains(searchText.lowercased()) ||
                    entry.room.name.lowercased().contains(searchText.lowercased()) ||
                    entry.speakerWithRoles.contains { speakerWithRole in
                        let speaker = speakerWithRole.speaker
                        return speaker.firstName.lowercased().contains(searchText.lowercased()) ||
                        speaker.lastName.lowercased().contains(searchText.lowercased()) ||
                        speaker.company.lowercased().contains(searchText.lowercased()) ||
                        speaker.jobTitle.lowercased().contains(searchText.lowercased())
                    } ||
                    entry.format.label.lowercased().contains(searchText.lowercased())
                }
            }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(Array(searchResults.keys), id: \.self) { start in
                    Section(header: HStack {
                        VStack { Divider() }
                        Text(timeFormatter(time: start))
                            .font(.headline)
                        VStack { Divider() }
                    }                        )
                    {
                        ForEach(searchResults[start]!, id: \.self) { entry in
                            let formatColor = Color(hexStringToUIColor(hex: entry.format.hexColor))
                            NavigationLink(destination: ProgramEntryDetailView(id: entry.id)) {
                                HStack {
                                    Divider()
                                        .frame(maxWidth:2)
                                        .background(formatColor)
                                    VStack (alignment: .leading) {
                                        ProgramEntryView(programEntry: entry, formatColor: formatColor)
                                    }
                                }
                            }
                        }
                    }
                }
                
            }
            .navigationTitle(title)
            .searchable(text: $searchText)
        }
    }
}

struct ProgramEntryView : View {
    let programEntry: ProgramEntry
    let formatColor: SwiftUI.Color
    @EnvironmentObject var viewModel: AppViewModel
    
    var body: some View {
        VStack (alignment: .leading) {
            HStack {
                Text(programEntry.format.label)
                    .foregroundColor(formatColor)
                    .font(.caption)
                Spacer()
                Image(systemName: viewModel.getFavStateIcon(entryId: programEntry.id))
                    .foregroundColor(.yellow)
            }
            VStack (alignment: .leading) {
                Text(timeFormatter(time: programEntry.timeRange.start) + " - " + timeFormatter(time: programEntry.timeRange.end) + " Uhr")
                Text(programEntry.room.name)
            }.font(.caption2)
            Text(programEntry.name).font(.title)
        }
        Spacer()
        SpeakerPreviewView(speakers: programEntry.speakerWithRoles)
    }
}

extension SpeakerWithRole: Identifiable {
    var id: String { speaker.id }
}

struct SpeakerPreviewView : View {
    let speakers: [SpeakerWithRole]
    
    var body: some View {
        ForEach(speakers) { speakerWithRole in
            HStack {
                SpeakerPreviewContent(speaker: speakerWithRole.speaker)
                Spacer()
                if let imgUrl = speakerWithRole.speaker.imgPreview {
                    SpeakerImageView(url: imgUrl)
                } else {
                    EmptyView()
                }
            }
        }
    }
}
