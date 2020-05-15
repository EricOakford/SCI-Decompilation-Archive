;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmInsideCasino) ;310
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use RandCyc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	gambleTimer
)
(instance rm310 of LLRoom
	(properties
		picture rmInsideCasino
		horizon 93
		north rmElevatorBottom
		south rmOutsideCasino
	)
	
	(method (init &tmp temp0)
		(LoadMany SOUND 310 312)
		(LoadMany VIEW 312 310 311)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 189 0 0 116 0 116 78 90 108 26 108 2 131 2 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 317 189 317 156 300 156 225 125 204 112 178 102 178 0 319 0 319 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 26 157 112 157 112 174 26 174
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 28 127 59 126 112 126 112 135 108 139 67 144 47 145 28 138
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 26 113 106 113 106 119 25 119
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 302 178 259 178 247 180 202 177 202 166 220 156 284 156 302 164
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 193 118 239 141 239 148 209 148 179 154 153 154 136 141 136 131
					yourself:
				)
		)
		(switch prevRoomNum
			(rmOutsideCasino (ego setPri: -1))
			(rmElevatorBottom
				(if (< (ego x?) 110) (ego x: 110))
				(if (> (ego x?) 170) (ego x: 170))
			)
			(rmBlackjack 0)
			(rmSlots (ego z: 0 show:))
			(else  (ego posn: 160 160))
		)
		(ego init:)
		(super init:)
		(if (!= prevRoomNum rmElevatorBottom)
			(theMusic number: 310 loop: -1 flags: mNOPAUSE play: 90)
		)
		(soundFx number: 312 loop: -1 flags: mNOPAUSE play:)
		(= gambleTimer (Random 400 200))
		(oldlady cycleSpeed: howFast init: setScript: sGamble 0 0)
		(man1
			cycleSpeed: howFast
			init:
			setScript: (sGamble new:) 0 2
		)
		(man1eyes
			cycleSpeed: howFast
			init:
			setScript: (sBlink new:)
		)
		(shortman
			cycleSpeed: howFast
			init:
			setScript: (sGamble new:) 0 0
		)
		(babe
			cycleSpeed: howFast
			init:
			setScript: (sGamble new:) 0 7
		)
		(jane
			cycleSpeed: howFast
			init:
			setScript: (sGamble new:) 0 0
		)
		(rodney
			cycleSpeed: howFast
			init:
			setScript: (sGamble new:) 0 0
		)
		(if (theGame detailLevel:)
			(lightBulb cycleSpeed: howFast setCycle: Forward init:)
			(flashball cycleSpeed: howFast setCycle: Forward init:)
			(flashers cycleSpeed: howFast setCycle: Forward init:)
			(flashingballs2 cycleSpeed: howFast setCycle: Forward init:)
			(flashingsteps cycleSpeed: howFast setCycle: Forward init:)
			(siren cycleSpeed: howFast setCycle: Forward init:)
			(chaser1 cycleSpeed: howFast setCycle: Forward init:)
			(chaser2 cycleSpeed: howFast setCycle: Forward init:)
			(chaser3 cycleSpeed: howFast setCycle: Forward init:)
			(chaser4 cycleSpeed: howFast setCycle: Forward init:)
			(screen1 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen2 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen3 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen4 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen5 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen6 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen7 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen8 cycleSpeed: howFast setCycle: RandCycle init:)
			(screen9 cycleSpeed: howFast setCycle: RandCycle init:)
		)
		(blackjack approachVerbs: verbDo verbUse init:)
		(BJ4 approachVerbs: verbDo verbUse init:)
		(BJ2 approachVerbs: verbDo verbUse init:)
		(slots approachVerbs: verbDo verbUse init:)
		(slots2 approachVerbs: verbDo verbUse init:)
		(paintingLeft init:)
		(paintingRight init:)
		(farRoom init:)
		(planterLeft init:)
		(planterRight init:)
		(fSlotMachines init:)
		(fBlackjackMachines init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and gambleTimer (== (-- gambleTimer) 1))
			(= gambleTimer (Random 200 400))
			(if (== (soundFx vol?) 127)
				(soundFx fade: 90 5 1 0)
			else
				(soundFx fade: 127 5 1 0)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 310 0)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(soundFx fade:)
		(cond 
			((== n 300) (ego setPri: 6) (theMusic fade:))
			((== n 320) (theMusic fade:))
		)
	)
)

(instance sBlink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 2 20)))
			(1
				(client setCel: 0 setCycle: EndLoop self)
			)
			(2 (self init:))
		)
	)
)

(instance sGamble of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 6)))
			(1
				(cond 
					(register
						(client setCel: 0 setLoop: register setCycle: EndLoop)
						(= seconds (Random 2 4))
					)
					((== client jane)
						(client setCel: 0 setCycle: Forward)
						(= seconds (Random 1 2))
					)
					(else (client setCel: 0 setCycle: EndLoop self))
				)
			)
			(2
				(if register
					(client
						setCel: 0
						setLoop: (+ register 1)
						setCycle: (if (== client babe) Forward else EndLoop)
					)
					(= seconds (Random 2 4))
				else
					(client setCel: 0)
					(self init:)
				)
			)
			(3 (self init:))
		)
	)
)

(instance lightBulb of Prop
	(properties
		x 223
		y 132
		view 310
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance chaser1 of Prop
	(properties
		x 273
		y 173
		view 310
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance chaser2 of Prop
	(properties
		x 230
		y 181
		view 310
		loop 2
		priority 12
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance screen1 of Prop
	(properties
		x 219
		y 149
		description {the screen}
		view 310
		loop 3
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance screen2 of Prop
	(properties
		x 251
		y 146
		description {the screen}
		view 310
		loop 4
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance screen3 of Prop
	(properties
		x 274
		y 141
		description {the screen}
		view 310
		loop 5
		priority 12
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance flashball of Prop
	(properties
		x 71
		y 127
		view 310
		loop 6
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
)

(instance flashers of Prop
	(properties
		x 84
		y 166
		view 310
		loop 7
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
)

(instance chaser3 of Prop
	(properties
		x 73
		y 178
		view 310
		loop 8
		priority 12
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance screen4 of Prop
	(properties
		x 46
		y 132
		description {the screen}
		view 310
		loop 9
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance screen5 of Prop
	(properties
		x 87
		y 137
		description {the screen}
		view 310
		loop 10
		priority 13
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance flashingballs2 of Prop
	(properties
		x 82
		y 79
		view 310
		loop 11
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
)

(instance flashingsteps of Prop
	(properties
		x 46
		y 103
		view 310
		loop 12
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
)

(instance screen6 of Prop
	(properties
		x 87
		y 109
		description {the screen}
		view 310
		loop 13
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance screen7 of Prop
	(properties
		x 54
		y 105
		description {the screen}
		view 310
		loop 14
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance chaser4 of Prop
	(properties
		x 189
		y 150
		view 311
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
)

(instance screen8 of Prop
	(properties
		x 184
		y 122
		description {the screen}
		view 311
		loop 1
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance screen9 of Prop
	(properties
		x 208
		y 122
		description {the screen}
		view 311
		loop 2
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance siren of Prop
	(properties
		x 210
		y 107
		view 311
		loop 4
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
)

(instance oldlady of Prop
	(properties
		x 158
		y 142
		description {Bertha}
		view 312
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 310 1))
			(5 (Print 310 2))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance man1 of Person
	(properties
		x 176
		y 152
		description {Ernie}
		view 312
		loop 2
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbLook) (== theVerb verbTalk))
			(oldlady doVerb: theVerb theItem)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance man1eyes of Prop
	(properties
		x 179
		y 153
		z 33
		description {Ernie}
		view 312
		loop 4
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(man1 doVerb: theVerb theItem)
	)
)

(instance shortman of Prop
	(properties
		x 220
		y 145
		description {Bert}
		view 312
		loop 5
		priority 11
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbLook) (== theVerb verbTalk))
			(oldlady doVerb: theVerb theItem)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance babe of Person
	(properties
		x 284
		y 176
		description {the babe}
		view 312
		loop 7
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbLook) (== theVerb verbTalk))
			(oldlady doVerb: theVerb theItem)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance jane of Person
	(properties
		x 56
		y 137
		z 37
		description {Jane}
		view 312
		loop 9
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbLook) (== theVerb verbTalk))
			(oldlady doVerb: theVerb theItem)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance rodney of Prop
	(properties
		x 51
		y 128
		description {Rodney}
		view 312
		loop 10
		priority 9
		signal (| ignrAct fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbLook) (== theVerb verbTalk))
			(oldlady doVerb: theVerb theItem)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance blackjack of Feature
	(properties
		x 84
		y 145
		nsTop 122
		nsLeft 68
		nsBottom 169
		nsRight 100
		description {a blackjack machine}
		sightAngle 40
		approachX 95
		approachY 174
		lookStr {You can remember when blackjack was always played at a real table with a real dealer. Now all you see are video machines.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (< dollars 10000)
					(Printf 310 3 dollars)
					(theMusic fade:)
					(curRoom newRoom: rmBlackjack)
				else
					(Print 310 4)
				)
			)
			(verbUse
				(switch theItem
					(iWallet (self doVerb: verbDo))
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

(instance slots of Feature
	(properties
		x 223
		y 154
		nsTop 139
		nsLeft 212
		nsBottom 169
		nsRight 234
		description {a slot machine}
		sightAngle 40
		approachX 202
		approachY 174
		lookStr {You can remember when slot machines had real wheels and gears. Now all you see are video machines.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (< dollars 10000)
					(Print 310 5)
					(theMusic fade:)
					(curRoom newRoom: rmSlots)
				else
					(Print 310 4)
				)
			)
			(verbUse
				(switch theItem
					(iWallet (self doVerb: verbDo))
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

(instance BJ4 of Feature
	(properties
		x 81
		y 137
		nsTop 92
		nsLeft 67
		nsBottom 122
		nsRight 96
		sightAngle 40
		approachX 94
		approachY 145
	)
	
	(method (doVerb theVerb theItem)
		(blackjack doVerb: theVerb theItem)
	)
)

(instance BJ2 of Feature
	(properties
		x 53
		y 142
		nsTop 125
		nsLeft 41
		nsBottom 159
		nsRight 65
		sightAngle 40
		approachX 36
		approachY 174
	)
	
	(method (doVerb theVerb theItem)
		(blackjack doVerb: theVerb theItem)
	)
)

(instance slots2 of Feature
	(properties
		x 248
		y 150
		nsTop 134
		nsLeft 235
		nsBottom 167
		nsRight 262
		sightAngle 40
		approachX 256
		approachY 179
	)
	
	(method (doVerb theVerb theItem)
		(slots doVerb: theVerb theItem)
	)
)

(instance paintingLeft of Feature
	(properties
		x 46
		y 108
		z 51
		nsTop 46
		nsLeft 17
		nsBottom 69
		nsRight 76
		description {the painting}
		sightAngle 40
		lookStr {What a lovely painting, although it does seem strangely familiar.}
	)
)

(instance paintingRight of Feature
	(properties
		x 268
		y 141
		z 56
		nsTop 60
		nsLeft 231
		nsBottom 110
		nsRight 306
		description {the painting}
		sightAngle 40
		lookStr {You never tire of art like this!}
	)
)

(instance farRoom of Feature
	(properties
		x 158
		y 59
		nsTop 37
		nsLeft 92
		nsBottom 81
		nsRight 224
		description {the next room}
		sightAngle 40
		lookStr {Peering through the casino, you can see the next room and a lovely planter filled with colorful plants.}
	)
)

(instance planterLeft of Feature
	(properties
		x 40
		y 93
		nsTop 72
		nsBottom 114
		nsRight 80
		description {the planter}
		sightAngle 40
		lookStr {You just love these colorful plastic plants.}
	)
)

(instance planterRight of Feature
	(properties
		x 255
		y 122
		nsTop 91
		nsLeft 192
		nsBottom 153
		nsRight 319
		description {the planter}
		sightAngle 40
		lookStr {You just love that planter filled with plastic plants.}
	)
)

(instance fSlotMachines of Feature
	(properties
		x 230
		y 140
		nsTop 101
		nsLeft 170
		nsBottom 180
		nsRight 291
		description {the slot machines}
		sightAngle 40
		lookStr {Exciting video slot machines await your money.}
	)
)

(instance fBlackjackMachines of Feature
	(properties
		x 73
		y 135
		nsTop 95
		nsLeft 42
		nsBottom 176
		nsRight 105
		description {the video blackjack machines}
		sightAngle 40
		lookStr {The latest state-of-the-gambling-art video blackjack machines seem to draw your billfold from your pocket directly to their yawning maws!}
	)
)
