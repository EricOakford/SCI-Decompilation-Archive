;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmBackroom) ;140
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	waitingToBlockStairs
	gaveWarning
	downstairsMessage
	[local3 17]
	moveTimer
)
(instance rm140 of LLRoom
	(properties
		picture rmBackroom
		north rmHooker
	)
	
	(method (init)
		(LoadMany VIEW 140 142)
		(LoadMany SOUND 140 148)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(if (and (!= tvChannel 7) (ego has: iRemoteControl))
			(Load VIEW 141)
			(Load TEXT 141)
		)
		(pimp init:)
		(switch prevRoomNum
			(rmInsideBar
				(Bclr fPaidPimp)
				(ego posn: 20 155)
			)
			(rmHooker
				(HandsOff)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								10 145 10 187 266 187 266 148 208 148 208 138 266 138
								258 116 205 116 205 122 199 122 67 0 319 0 319 189 0 189
								0 145
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 153 156 226 156 226 179 203 179 153 175
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 48 164 92 164 92 180 48 180
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 0 0 62 0 194 122 194 128 164 140 0 140
							yourself:
						)
					setScript: sFromHooker
				)
				(if (Btst fPaidPimp)
					(pimp
						setLoop: 7
						setCel: 4
						setPri: 11
						approachX: 221
						approachY: 148
					)
				)
				(= gaveWarning TRUE)
			)
			(else  (ego posn: 40 160))
		)
		(ego init: illegalBits: cWHITE)
		(super init:)
		(if (Btst fTVOn)
			(theMusic
				loop: -1
				flags: mNOPAUSE
				number:
					(switch tvChannel
						(0 141)
						(1 142)
						(2 143)
						(3 144)
						(4 145)
						(5 146)
						(6 147)
						(7 148)
					)
			)
			(if
				(or
					(!= tvChannel 7)
					(and (== tvChannel 7) (== prevRoomNum 110))
				)
				(theMusic play:)
			)
		else
			(theMusic fade: 90 15 5 0)
		)
		(pimp cycleSpeed: howFast approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk)
		(if (!= (pimp loop?) 7) (pimp setCycle: Forward))
		(tv init: approachVerbs: verbDo verbUse verbZipper verbTaste 2)
		(mooseTail cycleSpeed: howFast init: setScript: sTail)
		(moose init:)
		(boxes init:)
		(stairs init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(theDoorFeature init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(thePeephole init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(barrel1 init:)
		(barrel2 init:)
		(if (Btst fTVOn)
			(tvLights
				cycleSpeed: howFast
				init:
				setCel: 255
				approachVerbs: verbDo verbUse verbZipper verbTaste verbLook
			)
		)
		(cond 
			((== tvChannel 7)
				(pimp
					x: 235
					y: 146
					setLoop: 6
					setPri: 11
					approachX: 221
					approachY: 148
					setCycle: RandCycle
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								10 145 10 187 266 187 266 148 208 148 208 138
								266 138 258 116 205 116 205 122 199 122 67 0
								319 0 319 189 0 189 0 145
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 153 156 226 156 226 179 203 179 153 175
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 48 164 92 164 92 180 48 180
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 0 0 62 0 194 122 194 128 164 140 0 140
							yourself:
						)
				)
			)
			((not (Btst fPaidPimp))
				(if (not (pimp script?)) (pimp setScript: sPimp))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								10 145 10 187 91 187 267 187 267 135 259 124
								197 124 169 140 0 140 0 0 319 0 319 189 0 189 0 145
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 153 156 226 156 226 179 203 179 153 175
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 48 164 92 164 92 180 48 180
							yourself:
						)
				)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (< (ego y?) 116) (!= script sFromHooker))
			(ego setPri: 8)
			(if (not waitingToBlockStairs)
				(= waitingToBlockStairs TRUE)
				(HandsOff)
				(ego setMotion: PolyPath 66 0)
			)
		else
			(if (== (ego priority?) 8) (ego setPri: -1))
			(= waitingToBlockStairs 0)
		)
		(cond 
			(script)
			((< (ego y?) 45) (HandsOff) (curRoom setScript: sToHooker))
			(
			(and (== (pimp loop?) 6) (ego inRect: 194 146 261 159))
				(if (not moveTimer)
					(Print 140 0 #at -1 20)
					(= moveTimer 300)
				else
					(-- moveTimer)
				)
			)
			(
				(and
					(== gaveWarning FALSE)
					(!= tvChannel 7)
					(ego inRect: 180 116 270 136)
				)
				(= gaveWarning TRUE)
				(Print 140 1 #at -1 20)
			)
			(
				(and
					(== downstairsMessage FALSE)
					(== prevRoomNum rmHooker)
					(== (pimp loop?) 7)
					(> (ego y?) 138)
				)
				(= downstairsMessage TRUE)
				(Print 140 2 #at -1 20)
				(Bclr fPaidPimp)
				(pimp setLoop: 7 setCel: 4 setCycle: BegLoop pimp)
				((curRoom obstacles?) dispose:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								10 145 10 187 91 187 267 187 267 135 259 124 197 124
								169 140 0 140 0 0 319 0 319 189 0 189 0 145
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 153 156 226 156 226 179 203 179 153 175
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 48 164 92 164 92 180 48 180
							yourself:
						)
				)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(Printf 140 3
					(if (Btst fGotPimpToWatchTV) {watching TV} else {blocking the stairs})
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(UnLoad TEXT 141)
		(super newRoom: n)
	)
)

(instance sPimp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 5 25)))
			(1
				(pimp setLoop: 0 setCel: 0 setCycle: Forward)
				(= seconds (Random 3 8))
			)
			(2
				(if (== (pimp loop?) 7) (pimp setLoop: 1))
				(pimp setCel: 0 setCycle: EndLoop self)
			)
			(3
				(pimp setCel: 0)
				(= seconds (Random 3 8))
			)
			(4
				(pimp setLoop: 1 setCel: 0 setCycle: EndLoop)
				(= seconds (Random 3 8))
			)
			(5
				(self start: (if (Random 0 1) 0 else 2) init:)
			)
		)
	)
)

(instance sToHooker of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 66 0 self)
			)
			(1 (curRoom newRoom: rmHooker))
		)
	)
)

(instance sFromHooker of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 66 y: 2 setPri: 8 setMotion: MoveTo 113 46 self)
			)
			(1
				(ego setMotion: MoveTo 194 121 self)
			)
			(2
				(HandsOn)
				(= waitingToBlockStairs TRUE)
				(self dispose:)
			)
		)
	)
)

(instance sDoChannel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 141
					setLoop: 0
					cycleSpeed: howFast
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1 (= seconds 3))
			(2
				(soundFx number: 140 play:)
				(if (not (Btst fTVOn))
					(Print 140 4 #at -1 20)
					(SolvePuzzle fTurnedOnTV 3)
					(Bset fTVOn)
					(tvLights cycleSpeed: howFast init: setCycle: EndLoop)
				else
					(Print 140 5 #at -1 20)
					(++ tvChannel)
				)
				(theMusic
					vol:
					loop: -1
					number:
						(switch tvChannel
							(0 141)
							(1 142)
							(2 143)
							(3 144)
							(4 145)
							(5 146)
							(6 147)
							(7 148)
						)
					play:
				)
				(switch tvChannel
					(1 (UnLoad SOUND 141))
					(2 (UnLoad SOUND 142))
					(3 (UnLoad SOUND 143))
					(4 (UnLoad SOUND 144))
					(5 (UnLoad SOUND 145))
					(6 (UnLoad SOUND 146))
					(7 (UnLoad SOUND 147))
				)
				(ego setCycle: BegLoop)
				(= seconds 3)
			)
			(3
				(if (< tvChannel 7) (HandsOn))
				(NormalEgo 2)
				(Print 141 (* 2 tvChannel) #at -1 20)
				(= seconds 3)
			)
			(4
				(cond 
					((== tvChannel 4) (Print 141 (+ 1 (* 2 tvChannel)) #at -1 20))
					((== tvChannel 7) (Bset fGotPimpToWatchTV) (= state (+ state 1)))
					(else (Printf 140 6 141 (+ 1 (* 2 tvChannel)) #at -1 20))
				)
				(= seconds 3)
			)
			(5
				(Printf 140 7
					(if (Btst fPaidPimp)
						{.}
					else
						{, but not interested enough to leave his post guarding the stairs.}
					)
					#at -1 20
				)
				(self dispose:)
			)
			(6
				(pimp setScript: 0)
				(if (Btst fPaidPimp)
					(Bclr fPaidPimp)
					(++ state)
					(= cycles 1)
				else
					(pimp
						setLoop: 4
						setCel: 0
						cycleSpeed: (+ 3 howFast)
						setCycle: EndLoop self
					)
				)
			)
			(7
				(pimp
					setCel: 0
					setLoop: 5
					cycleSpeed: (+ 1 howFast)
					setCycle: EndLoop self
				)
			)
			(8
				(HandsOn)
				(pimp
					x: 235
					y: 146
					approachX: 221
					approachY: 148
					setCel: 0
					setLoop: 6
					setPri: 11
					setCycle: RandCycle
					cycleSpeed: howFast
				)
				(= seconds 3)
			)
			(9
				(SolvePuzzle fPimpDistracted 8)
				(Print 140 8 #at -1 20)
				((curRoom obstacles?) dispose:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								10 145 10 187 266 187 266 148 208 148 208 138 266 138
								258 116 205 116 205 122 199 122 67 0 319 0 319 189 0 189
								0 145
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 153 156 226 156 226 179 203 179 153 175
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 48 164 92 164 92 180 48 180
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init: 0 0 62 0 194 122 194 128 164 140 0 140
							yourself:
						)
				)
				(self dispose:)
			)
		)
	)
)

(instance sTail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mooseTail setCycle: RandCycle)
				(= seconds 3)
			)
			(1
				(mooseTail setCycle: 0 stopUpd:)
				(= seconds (Random 7 20))
			)
			(2 (self init:))
		)
	)
)

(instance sToBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: MoveTo 20 (ego y?) self)
			)
			(1 (curRoom newRoom: rmInsideBar))
		)
	)
)

(instance pimp of Person
	(properties
		x 208
		y 122
		description {the pimp}
		sightAngle 40
		approachX 197
		approachY 124
		lookStr {You can tell by his glazed stare, he's no rocket scientist!}
		view 142
		signal (| ignrAct fixedLoop)
	)
	
	(method (doVerb theVerb theItem)
		(if (Btst fGotPimpToWatchTV)
			(Print 140 9)
		else
			(switch theVerb
				(verbDo (Print 140 10))
				(verbZipper (Print 140 11 #at -1 20))
				(verbTaste (Print 140 12))
				(verbTalk
					(Print 140 13 #at -1 20)
					(Print 140 14 #at -1 20)
					(Print 140 15 #at -1 140)
				)
				(verbUse
					(switch theItem
						(iWallet
							(cond 
								((Btst fGotPimpToWatchTV) (Print 140 16))
								((< dollars 200) (Print 140 17))
								(else
									(= gaveWarning TRUE)
									(= dollars (- dollars 200))
									(Print 140 18 #at -1 20)
									(if (Btst fScoredHooker) (Print 140 19 #at -1 20))
									(if (not (Btst fPaidPimp))
										(Bset fPaidPimp)
										(pimp
											setLoop: 7
											setCel: 0
											setCycle: EndLoop
											setPri: 11
											approachX: 221
											approachY: 148
											setScript: 0
										)
									)
									((curRoom obstacles?) dispose:)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													10 145 10 187 266 187 266 148 208 148 208 138
													266 138 258 116 205 116 205 122 199 122 67 0
													319 0 319 189 0 189 0 145
												yourself:
											)
											((Polygon new:)
												type: PBarredAccess
												init: 153 156 226 156 226 179 203 179 153 175
												yourself:
											)
											((Polygon new:)
												type: PBarredAccess
												init: 48 164 92 164 92 180 48 180
												yourself:
											)
											((Polygon new:)
												type: PBarredAccess
												init: 0 0 62 0 194 122 194 128 164 140 0 140
												yourself:
											)
									)
									(Print 140 20 #at -1 20)
								)
							)
						)
						(iRemoteControl (Print 140 21))
						(iRing (Print 140 22))
						(iRibbon (Print 140 23))
						(iHammer
							(Print 140 24)
							(Print 140 25 #at -1 140)
						)
						(iPocketKnife
							(Print 140 26)
							(Print 140 27 #at -1 140)
						)
						(else 
							(Printf 140 28 ((Inventory at: theItem) description?))
						)
					)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(sPimp start: (if (Random 0 1) 0 else 2))
		(pimp
			setScript: sPimp
			setPri: -1
			approachX: 197
			approachY: 124
		)
	)
)

(instance moose of Feature
	(properties
		x 97
		y 129
		z 36
		nsTop 78
		nsLeft 80
		nsBottom 109
		nsRight 115
		description {the moose}
		sightAngle 40
		lookStr {And you thought that moosehead over Lefty's bar was stuffed!_}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 140 29))
			(verbZipper (Print 140 30))
			(verbTalk
				(Print 140 31)
				(Print 140 32 #at -1 140)
			)
			(verbTaste (Print 140 33))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance mooseTail of Prop
	(properties
		x 117
		y 156
		z 71
		description {the moose's tail}
		lookStr {And all this time you thought that moosehead over Lefty's bar was stuffed!}
		view 140
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 140 34))
			(else 
				(moose doVerb: theVerb theItem)
			)
		)
	)
)

(instance tvLights of Prop
	(properties
		x 180
		y 133
		description {the television set}
		sightAngle 40
		approachX 228
		approachY 155
		view 140
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(tv doVerb: theVerb theItem)
	)
)

(instance theDoorFeature of Feature
	(properties
		x 1
		y 146
		nsTop 84
		nsBottom 142
		nsRight 34
		description {the door}
		sightAngle 40
		approachX 29
		approachY 146
		lookStr {It's the door from Lefty's bar. Its far side looks even better than this side.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sToBar)
			)
			(verbTalk
				(Print 140 35)
				(Print 140 36)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tv of Feature
	(properties
		x 190
		y 173
		z 37
		nsTop 122
		nsLeft 167
		nsBottom 151
		nsRight 213
		description {the television set}
		sightAngle 40
		approachX 228
		approachY 155
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fTVOn)
					(switch tvChannel
						(4
							(Print 141 (+ 1 (* 2 tvChannel)) 67 -1 20)
						)
						(7 (Print 140 37))
						(else 
							(Printf 140 6 141 (+ 1 (* 2 tvChannel)) 67 -1 20)
						)
					)
				else
					(Print 140 38)
				)
			)
			(verbDo (Print 140 39))
			(verbUse
				(switch theItem
					(iRemoteControl
						(cond 
							((or (> (ego y?) 162) (< (ego x?) 183)) (Print 140 40))
							((== tvChannel 7) (Print 140 41))
							(else (HandsOff) (curRoom setScript: sDoChannel))
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance boxes of Feature
	(properties
		x 244
		y 92
		nsTop 69
		nsLeft 194
		nsBottom 115
		nsRight 294
		description {some boxes}
		sightAngle 40
		lookStr {They're boxes, all right.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 140 42)
				(Print 140 43)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance stairs of Feature
	(properties
		x 143
		y 14
		description {the staircase}
		sightAngle 180
		onMeCheck $1000
		approachX 197
		approachY 124
		lookStr {You wonder what palace of earthly delights resides above you.}
	)
)

(instance barrel1 of Feature
	(properties
		x 47
		y 120
		nsTop 104
		nsLeft 34
		nsBottom 137
		nsRight 61
		description {the barrel}
		sightAngle 40
		lookStr {That's how Lefty buys his fine wines!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 140 44))
			(verbTaste (Print 140 45))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance barrel2 of Feature
	(properties
		x 144
		y 123
		nsTop 110
		nsLeft 126
		nsBottom 136
		nsRight 163
		description {the barrel}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(barrel1 doVerb: theVerb theItem)
	)
)

(instance thePeephole of Feature
	(properties
		x 12
		y 148
		z 38
		nsTop 108
		nsLeft 6
		nsBottom 113
		nsRight 18
		description {the peephole}
		sightAngle 40
		approachX 29
		approachY 146
		lookStr {How interesting. This door has a small peephole. You suspect it may be used to communicate with someone on the other side.}
	)
	
	(method (doVerb theVerb theItem)
		(theDoorFeature doVerb: theVerb theItem)
	)
)
