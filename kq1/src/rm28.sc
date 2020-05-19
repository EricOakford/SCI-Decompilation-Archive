;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm28 0
)

(instance rm28 of Room
	(properties
		picture 28
		horizon 57
		north 37
		east 29
		south 21
		west 27
	)
	
	(method (init)
		(LoadMany VIEW 228 2 45)
		(LoadMany SOUND 16 15 83 29)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
				(else  IRISOUT)
			)
		)
		(super init:)
		(door setPri: 8 init: stopUpd:)
		(cane setPri: 9 init: stopUpd:)
		(smoke init: setCycle: Forward)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_18 262 (ego x?) 41) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 213 (ego x?) 1) 188)
			)
			(west
				(ego posn: 3 (proc0_17 188 (ego y?) (+ horizon 2)))
			)
			(east
				(ego posn: 297 (proc0_17 162 (ego y?) (+ horizon 2)))
			)
			(else 
				(ego posn: 137 141 loop: 2)
				(door cel: 4 setScript: closeIt)
			)
		)
		(ego init:)
		(NormalEgo)
		(if (!= prevRoomNum 65)
			(if (> (Random 1 100) 50)
				(Bset fWitchIsHome)
			else
				(Bclr fWitchIsHome)
			)
		)
		(cond 
			((Btst fKilledWitch)
				(Bclr fWitchIsHome)
			)
			((== prevRoomNum 65) 0)
		)
		(addToPics
			add: cookie1 cookie2 cookie3_a
			eachElementDo: #init
			doit:
		)
		(house5 init:)
		(candy_canes1 init:)
		(candy_canes init:)
		(candy_canes2 init:)
		(candy_canes3 init:)
		(candy_canes5 init:)
		(candy_canes4 init:)
		(chimney init:)
		(tree init:)
		(tree1 init:)
		(tree3 init:)
		(tree2 init:)
		(backwind init:)
		(house init:)
		(house1 init:)
		(house3 init:)
		(house2 init:)
		(house4 init:)
		(house7 init:)
		(house6 init:)
		(house9 init:)
		(house8 init:)
		(house10 init:)
		(steps2 init:)
		(steps init:)
		(roof init:)
		(roof1 init:)
		(roof2 init:)
		(roof3 init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((or (Said 'look,check/window') (MouseClaimed event 172 91 191 112))
				(if (ego inRect: 150 120 220 155)
					(Print 28 0)
				else
					(Print 28 1)
				)
			)
			((Said 'smell,sniff/building,cake')
				(Print 28 2)
			)
			((Said 'look,check/building')
				(if (ego inRect: 34 100 236 190)
					(Print 28 3)
				else
					(Print 28 4)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 28 5)
					)
					((Said '/witch')
						(Print 28 6)
					)
					((Said '/path')
						(Print 28 7)
					)
					((Said '/candy')
						(Print 28 8)
					)
					((Said '/fence')
						(Print 28 9)
					)
					((Said '/cake')
						(Print 28 10)
					)
					((Said '/frosting,frosting')
						(Print 28 11)
					)
				)
			)
			((Said 'knock')
				(cond 
					((not (ego inRect: 114 129 151 138))
						(CantReach)
					)
					((Btst fInvisible)
						(Print 28 12)
					)
					(else
						(self setScript: knockDoor)
					)
				)
			)
			((Said 'open,open/door')
				(if (not (ego inRect: 114 129 151 138))
					(CantReach)
				else
					(door setScript: openIt)
				)
			)
			((Said 'close,shut/door')
				(if (not (ego inRect: 114 129 151 138))
					(CantReach)
				else
					(Print 28 13)
				)
			)
			((Said 'bend/window')
				(if (ego inRect: 150 120 220 155)
					(Print 28 14)
				else
					(Print 28 15)
				)
			)
			((Said 'bend/door')
				(if (ego inRect: 2 106 316 166)
					(Print 28 16)
				else
					(Print 28 17)
				)
			)
			((Said 'unlock/door,lock')
				(Print 28 18)
			)
			((Said 'eat,consume>')
				(cond 
					((Said '/fence')
						(if (ego inRect: 114 146 245 162)
							(Print 28 19)
						else
							(Print 28 20)
						)
					)
					((Said '/building,cake,frosting,frosting,cake,ceiling,door,window')
						(cond 
							((not (& (ego onControl: origin) cYELLOW))
								(Print 28 21)
							)
							((Btst fWitchIsHome)
								(Face ego 110 110)
								(Print 28 22)
								(SolvePuzzle fAteWitchHouse 2)
							)
							(else
								(Print 28 23)
								(SolvePuzzle fAteWitchHouse 2)
							)
						)
					)
					((Said '/candy')
						(Print 28 24)
					)
					((Said '/cookie')
						(Print 28 25)
					)
					((Said '/blossom')
						(Print 28 26)
					)
					((or (Said '/cane<candy') (Said '/stick<peppermint') (Said '/gumdrop'))
						(Print 28 27)
					)
					((Said '/chimney')
						(Print 28 28)
					)
					((or (Said '/man,boy[<cake]') (Said '/fairy,fairy[<cake]'))
						(Print 28 29)
					)
					(else
						(super handleEvent: event)
					)
				)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 68
		y 8
		view 228
		loop 3
		priority 10
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/smoke')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 28 30)
			)
		)
	)
)

(instance cane of View
	(properties
		x 182
		y 112
		description {window}
		view 228
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/window')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 28 0)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 111
		y 131
		view 228
		signal stopUpdOn
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			(
			(or (Said 'look,check/door') (MousedOn self event shiftDown))
				(if (> (ego y?) 130)
					(Print 28 31)
				else
					(Print 28 17)
				)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance knockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 45
					loop: 0
					cycleSpeed: (if (>= howFast 1) 1 else 0)
					cel: 0
					setMotion: 0
				)
				(= cycles 2)
			)
			(1
				((ScriptID 0 21) number: 29 loop: 2 play: self)
				(ego loop: 1 setCycle: Forward)
			)
			(2
				(ego loop: 0 cel: 0 setCycle: 0)
				(= cycles 2)
			)
			(3
				(ego view: 2 loop: 3 cel: 0)
				(NormalEgo)
				(if (Btst fWitchIsHome) (Print 28 32) else (Print 28 33))
				(self cue:)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance closeIt of Script
	(properties
		cycles 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(doorSound number: 16 loop: 1 play:)
				(door cel: 4 cycleSpeed: 0 setCycle: CycleTo 1 -1 self)
			)
			(2
				(doorSound number: 83 play:)
				(door setCycle: BegLoop self)
			)
			(3
				(door stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(doorSound number: 15 loop: 1 play:)
				(door cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				(doorSound stop:)
				(door stopUpd:)
				(HandsOn)
				(curRoom newRoom: 65)
			)
		)
	)
)

(instance cookie1 of RPicView
	(properties
		x 218
		y 169
		description {cookie}
		view 228
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (MousedOn self event) (Said 'look,check/cookie,man,fairy,boy,fairy'))
				(Print 28 34)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance cookie2 of RPicView
	(properties
		x 120
		y 175
		description {cookie}
		view 228
		loop 2
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (MousedOn self event) (Said 'look,check/cookie,man'))
				(Print 28 34)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance cookie3_a of RPicView
	(properties
		x 102
		y 160
		description {cookie}
		view 228
		loop 2
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((or (MousedOn self event) (Said 'look,check/cookie,man'))
				(Print 28 34)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance cookie3_b of NewFeature
	(properties
		x 218
		y 155
		noun '/cookie,man,boy[<cake]'
		nsTop 142
		nsLeft 212
		nsBottom 169
		nsRight 225
		description {gingerbread boy}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {These little gingerbread boys and girls look almost alive! How do you suppose they got here?}
	)
)

(instance candy_canes of NewFeature
	(properties
		x 84
		y 134
		noun '/cane<candy'
		nsTop 116
		nsLeft 74
		nsBottom 153
		nsRight 94
		description {candy canes}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The path to the gingerbread house is lined with the biggest, most tempting candy canes you've ever seen.}
	)
)

(instance candy_canes1 of NewFeature
	(properties
		x 120
		y 141
		noun '/cane<candy'
		nsTop 133
		nsLeft 112
		nsBottom 149
		nsRight 128
		description {candy canes}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The path to the gingerbread house is lined with the biggest, most tempting candy canes you've ever seen.}
	)
)

(instance candy_canes2 of NewFeature
	(properties
		x 134
		y 156
		noun '/cane<candy'
		nsTop 145
		nsLeft 129
		nsBottom 167
		nsRight 140
		description {candy canes}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The path to the gingerbread house is lined with the biggest, most tempting candy canes you've ever seen.}
	)
)

(instance candy_canes3 of NewFeature
	(properties
		x 186
		y 144
		noun '/cane<candy'
		nsTop 130
		nsLeft 176
		nsBottom 158
		nsRight 197
		description {candy canes}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The path to the gingerbread house is lined with the biggest, most tempting candy canes you've ever seen.}
	)
)

(instance candy_canes4 of NewFeature
	(properties
		x 201
		y 154
		noun '/cane<candy'
		nsTop 142
		nsLeft 197
		nsBottom 166
		nsRight 206
		description {candy canes}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The path to the gingerbread house is lined with the biggest, most tempting candy canes you've ever seen.}
	)
)

(instance candy_canes5 of NewFeature
	(properties
		x 219
		y 123
		noun '/cane<candy'
		nsTop 109
		nsLeft 211
		nsBottom 137
		nsRight 228
		description {candy canes}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The path to the gingerbread house is lined with the biggest, most tempting candy canes you've ever seen.}
	)
)

(instance chimney of NewFeature
	(properties
		x 74
		y 23
		noun '/chimney'
		nsTop 3
		nsLeft 56
		nsBottom 43
		nsRight 93
		description {chimney}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Even the chimney looks delicious.}
	)
)

(instance tree of NewFeature
	(properties
		x 306
		y 93
		noun '/ceder'
		nsTop -1
		nsLeft 294
		nsBottom 188
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Twisted, malformed trees overhang the gingerbread house. They almost seem to reach out for you.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 219
		y 27
		noun '/ceder'
		nsTop -1
		nsLeft 145
		nsBottom 55
		nsRight 294
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Twisted, malformed trees overhang the gingerbread house. They almost seem to reach out for you.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 271
		y 179
		noun '/ceder'
		nsTop 171
		nsLeft 249
		nsBottom 188
		nsRight 293
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Twisted, malformed trees overhang the gingerbread house. They almost seem to reach out for you.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 236
		y 185
		noun '/ceder'
		nsTop 183
		nsLeft 225
		nsBottom 188
		nsRight 248
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Twisted, malformed trees overhang the gingerbread house. They almost seem to reach out for you.}
	)
)

(instance house of NewFeature
	(properties
		x 104
		y 124
		noun '/building'
		nsTop 116
		nsLeft 95
		nsBottom 132
		nsRight 113
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house1 of NewFeature
	(properties
		x 182
		y 121
		noun '/building'
		nsTop 113
		nsLeft 154
		nsBottom 130
		nsRight 210
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house2 of NewFeature
	(properties
		x 225
		y 92
		noun '/building'
		nsTop 75
		nsLeft 196
		nsBottom 109
		nsRight 254
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house3 of NewFeature
	(properties
		x 167
		y 79
		noun '/building'
		nsTop 75
		nsLeft 139
		nsBottom 83
		nsRight 195
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house4 of NewFeature
	(properties
		x 158
		y 98
		noun '/building'
		nsTop 84
		nsLeft 152
		nsBottom 112
		nsRight 165
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house5 of NewFeature
	(properties
		x 77
		y 98
		noun '/building'
		nsTop 81
		nsLeft 47
		nsBottom 115
		nsRight 108
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house6 of NewFeature
	(properties
		x 100
		y 75
		noun '/building'
		nsTop 70
		nsLeft 62
		nsBottom 80
		nsRight 138
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house7 of NewFeature
	(properties
		x 38
		y 110
		noun '/building'
		nsTop 90
		nsLeft 31
		nsBottom 131
		nsRight 46
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house8 of NewFeature
	(properties
		x 59
		y 131
		noun '/building'
		nsTop 115
		nsLeft 46
		nsBottom 147
		nsRight 73
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house9 of NewFeature
	(properties
		x 104
		y 66
		noun '/building'
		nsTop 64
		nsLeft 87
		nsBottom 69
		nsRight 121
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance house10 of NewFeature
	(properties
		x 239
		y 113
		noun '/building'
		nsTop 109
		nsLeft 229
		nsBottom 118
		nsRight 249
		description {house}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is the most marvelous house you've ever seen! It seems like it's made out of a huge gingerbread cupcake, with frosting for a roof. The chimney is made of gummy bricks, the door and fence are made of candy canes, and sourball stones and gumdrops are scattered around the yard.}
	)
)

(instance steps of NewFeature
	(properties
		x 150
		y 138
		noun '/stair'
		nsTop 132
		nsLeft 129
		nsBottom 144
		nsRight 172
		description {steps}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The steps look like they're made out of vanilla fudge, but after years of being stepped on, they've gotten a wee bit filthy.}
	)
)

(instance steps2 of NewFeature
	(properties
		x 157
		y 148
		noun '/stair'
		nsTop 145
		nsLeft 140
		nsBottom 151
		nsRight 175
		description {steps}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The steps look like they're made out of vanilla fudge, but after years of being stepped on, they've gotten a wee bit filthy.}
	)
)

(instance roof of NewFeature
	(properties
		x 26
		y 78
		noun '/ceiling'
		nsTop 66
		nsLeft 11
		nsBottom 90
		nsRight 41
		description {roof}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The roof looks like it's made out of rich vanilla buttercream frosting!}
	)
)

(instance roof3 of NewFeature
	(properties
		x 49
		y 52
		noun '/ceiling'
		nsTop 33
		nsLeft 37
		nsBottom 71
		nsRight 62
		description {roof}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The roof looks like it's made out of rich vanilla buttercream frosting!}
	)
)

(instance roof2 of NewFeature
	(properties
		x 161
		y 58
		noun '/ceiling'
		nsTop 46
		nsLeft 62
		nsBottom 70
		nsRight 260
		description {roof}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The roof looks like it's made out of rich vanilla buttercream frosting!}
	)
)

(instance roof1 of NewFeature
	(properties
		x 118
		y 33
		noun '/ceiling'
		nsTop 21
		nsLeft 94
		nsBottom 46
		nsRight 142
		description {roof}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The roof looks like it's made out of rich vanilla buttercream frosting!}
	)
)

(instance backwind of NewFeature
	(properties
		x 234
		y 88
		noun '/window<back'
		nsTop 80
		nsLeft 228
		nsBottom 96
		nsRight 241
		description {window}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There's a small window at the back of the cupcake...er, the house.__But you can't see through the sugar-pane glass.}
	)
)

(instance doorSound of Sound
	(properties
		priority 10
	)
)
