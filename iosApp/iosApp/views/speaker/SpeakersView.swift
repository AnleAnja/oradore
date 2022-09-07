//
//  SpeakersView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

extension Speaker: Identifiable {}

struct SpeakersView: View {
    let speakers: [Speaker]
    
    @State private var searchText = ""
    
    private var searchResults: [Speaker] {
        let lowercasedSearchText = searchText.lowercased()
        if searchText.isEmpty {
            return speakers
        } else {
            return speakers.filter {
                $0.firstName.lowercased().contains(lowercasedSearchText) ||
                $0.lastName.lowercased().contains(lowercasedSearchText) ||
                $0.company.lowercased().contains(lowercasedSearchText) ||
                $0.jobTitle.lowercased().contains(lowercasedSearchText)
            }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(searchResults) { speaker in
                    NavigationLink(destination: SpeakerDetailView(speaker: speaker)) {
                        HStack {
                            if let imgUrl = speaker.imgPreview {
                                SpeakerImageView(url: imgUrl)
                            }
                            SpeakerPreviewContent(speaker: speaker)
                        }
                    }
                }
            }
            .navigationTitle("Speaker")
            .searchable(text: $searchText)
        }
    }
}
