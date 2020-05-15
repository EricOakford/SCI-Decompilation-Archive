;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmInsideChapel) ;410
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm410 0
)

(instance rm410 of LLRoom
	(properties
		lookStr {You are in a quickie marriage parlor. A flashing neon sign on the rear wall asks: 
		"Why wait? Marry the girl of your dreams today! You provide the girl, we'll do the rest for only $100!!"}
		picture rmInsideChapel
		south rmOutsideChapel
	)
	
	(method (init)
		(LoadMany VIEW 411 413 410)
		(LoadMany SOUND 410 411 321)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 172 189 140 133 183 128
						171 122 148 127 120 130 71 127 59 132 108 135 110 189
					yourself:
				)
		)
		(window1 init:)
		(window2 init:)
		(window3 init:)
		(pew1 init:)
		(pew2 init:)
		(bouquet init:)
		(candelabra init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(candelabra2 init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(fSign init:)
		(rightCandle
			cycleSpeed: howFast
			init:
			setCycle: RandCycle
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook
		)
		(leftCandle
			cycleSpeed: howFast
			init:
			setCycle: RandCycle
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook
		)
		(if debugging (Bset fGaveFawnEverything))
		(if (Btst fGaveFawnEverything)
			(fawn init: setLoop: 3 setCel: 0 x: 117 y: 135)
			(preacherHead cycleSpeed: howFast init:)
			(aPreacher cycleSpeed: howFast moveSpeed: howFast init:)
			(ego x: 137 init:)
		else
			(ego x: 137 y: 170 init:)
		)
		(theMusic number: 410 vol: 127 loop: -1 flags: 1 play:)
		(soundFx loop: 1 flags: mNOPAUSE)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (not script) (Btst fGaveFawnEverything))
			(curRoom setScript: sGetMarried)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(theMusic fade:)
	)
)

(instance sGetMarried of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fUnsuccessfulCeremony)) (Print 410 0 #at -1 19 #width 280))
				(ego setMotion: PolyPath 136 133 self)
			)
			(1
				(Face ego aPreacher)
				(= cycles 4)
			)
			(2
				(ego hide:)
				(fawn hide:)
				(egoAtAlter init: setCycle: EndLoop)
				(if (Btst 8)
					(Print 410 1 #at -1 20)
					(Print 410 2 #at 120 30)
					(Print 410 3 #at 120 30)
					(Print 410 4 #at 15 30)
				)
				(preacherHead setCycle: RandCycle 35 self)
				(aPreacher setLoop: 3 setCycle: ForwardCounter 7)
			)
			(3
				(egoAtAlter stopUpd:)
				(Print 410 5 #at -1 20)
				(preacherHead setCycle: RandCycle 35 self)
				(aPreacher setCycle: ForwardCounter 7)
			)
			(4
				(Print 410 6 #at -1 19 #width 280)
				(= seconds 3)
			)
			(5
				(Print 410 7 #at -1 140)
				(aPreacher setLoop: 2 setCel: 0 setCycle: BegLoop self)
			)
			(6
				(Print 410 8 #at -1 20)
				(Print 410 9 #at 120 30)
				(= seconds 3)
			)
			(7
				(aPreacher setLoop: 0)
				(preacherHead setCycle: RandCycle 20 self)
			)
			(8
				(Print 410 10 #at -1 20)
				(= seconds 3)
			)
			(9
				(Print 410 11 #at 120 30)
				(preacherHead setCycle: RandCycle 15 self)
			)
			(10
				(Print 410 12 #at -1 20)
				(= seconds 3)
			)
			(11
				(cond 
					((== dollars 100)
						(Print 410 13 #at 120 30)
						(Print 410 14 #at 15 30)
						(Print 410 15 #at -1 20)
						(theMusic stop:)
						(Print 410 16)
						(ego
							show:
							setCycle: Walk
							setMotion: PolyPath 136 175 self
						)
						(fawn show: loop: 2 cel: 4)
						(egoAtAlter dispose:)
						(self changeState: 22)
					)
					((< dollars 100) (self changeState: 17))
					(else
						(Bset fFawnInRoom)
						(Print 410 17 #at 120 30)
						(= dollars (- dollars 100))
						(preacherHead setCycle: RandCycle 35 self)
					)
				)
			)
			(12
				(Print 410 18 #at -1 20)
				(Print 410 19 #at -1 20)
				(SolvePuzzle fGetMarried 12)
				(Bclr fGaveFawnEverything)
				(Bclr fUnsuccessfulCeremony)
				(preacherHead setCycle: RandCycle 20 self)
			)
			(13
				(theMusic number: 411 vol: 127 loop: -1 flags: SND_DONE play:)
				(Print 410 20 #at -1 20)
				(Print 410 21 #at 120 30)
				(Print 410 22)
				(aPreacher dispose:)
				(preacherHead dispose:)
				(Print 410 23)
				(Print 410 24 #at 15 30)
				(ego show: loop: 2)
				(fawn
					show:
					loop: 2
					setCycle: Walk
					cycleSpeed: (+ 3 howFast)
					moveSpeed: (+ 3 howFast)
					setMotion: PolyPath 136 247 self
				)
				(egoAtAlter dispose:)
				(= seconds 3)
			)
			(14
				(Print 410 25 #at 120 30)
				(= seconds 3)
			)
			(15 (Print 410 26))
			(16
				(Bset fMarriedFawn)
				(HandsOn)
				(self dispose:)
			)
			(17
				(preacherHead setCycle: RandCycle 10 self)
			)
			(18
				(if (Btst fUnsuccessfulCeremony)
					(Print 410 27 #at -1 20)
					(Print 410 28 #at 120 30)
					(Print 410 29 #at -1 20)
					(ego
						show:
						setCycle: Walk
						setMotion: PolyPath 136 175 self
					)
					(fawn show: loop: 2 cel: 4)
					(egoAtAlter dispose:)
					(self changeState: 22)
				else
					(Bset fUnsuccessfulCeremony)
					(Print 410 30 #at 120 30)
					(Print 410 31 #at -1 20)
				)
				(Print 410 15 #at -1 20)
				(theMusic stop:)
				(= seconds 3)
			)
			(19
				(Print 410 32)
				(Print 410 33 #at 15 30)
				(ego
					show:
					setCycle: Walk
					setMotion: PolyPath 136 175 self
				)
				(fawn show: loop: 2 cel: 4)
				(egoAtAlter dispose:)
			)
			(20
				(Print 410 34 #at 15 30)
				(Face ego fawn)
				(= seconds 3)
			)
			(21
				(Print 410 35 #at 120 30)
				(soundFx number: 321 play:)
				(Print 410 36 #at -1 140)
				(= seconds 2)
			)
			(22
				(Print 410 37 #at 120 30)
				(ego setMotion: PolyPath 136 247 self)
			)
			(23 (curRoom newRoom: 400))
		)
	)
)

(instance preacherHead of Prop
	(properties
		view 413
		loop 1
	)
	
	(method (doit)
		(super doit:)
		(if (OneOf (aPreacher loop?) 0 3)
			(preacherHead
				x: (- (aPreacher x?) 1)
				y: (aPreacher y?)
				z: 27
			)
		else
			(preacherHead z: 1000)
		)
	)
	
	(method (doVerb theVerb theItem)
		(aPreacher doVerb: theVerb theItem)
	)
)

(instance aPreacher of Actor
	(properties
		x 127
		y 118
		description {Reverend Al}
		lookStr {He looks a lot like his brother, that bad comedian in the Lizard Lounge.}
		view 413
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Print 410 38)
				(Print 410 39 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fawn of Actor
	(properties
		x 119
		y 134
		description {Fawn}
		sightAngle 40
		view 612
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 410 40)
			)
			(verbTalk
				(Print 410 41)
				(Print 410 42)
			)
			(verbDo
				(Print 410 43)
			)
			(verbZipper
				(Print 410 44)
			)
			(verbTaste
				(Print 410 45)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance egoAtAlter of Prop
	(properties
		x 123
		y 133
		view 411
		signal ignrAct
	)
)

(instance rightCandle of Prop
	(properties
		x 181
		y 74
		description {the candle}
		sightAngle 40
		approachX 160
		approachY 130
		lookStr {You can barely tell they're made from pressed sawdust.}
		view 410
		loop 1
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 410 46)
				(Print 410 47)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance leftCandle of Prop
	(properties
		x 69
		y 76
		description {the candle}
		sightAngle 40
		approachX 95
		approachY 134
		lookStr {You can barely tell they're made from pressed sawdust.}
		view 410
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 410 46)
				(Print 410 47)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance window1 of Feature
	(properties
		x 274
		y 84
		nsTop 52
		nsLeft 259
		nsBottom 117
		nsRight 289
		description {the window}
		sightAngle 40
		lookStr {You can't see through the "stained glass" windows. They're fake, like everything else about this place.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance window2 of Feature
	(properties
		x 217
		y 82
		nsTop 52
		nsLeft 206
		nsBottom 112
		nsRight 228
		description {the window}
		sightAngle 40
		lookStr {You can't see through the "stained glass" windows. They're fake, like everything else about this place.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance window3 of Feature
	(properties
		x 29
		y 82
		nsTop 55
		nsLeft 11
		nsBottom 109
		nsRight 48
		description {the window}
		sightAngle 40
		lookStr {You can't see through the "stained glass" windows. They're fake, like everything else about this place.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fSign of Feature
	(properties
		x 112
		y 30
		nsTop 17
		nsLeft 77
		nsBottom 44
		nsRight 148
		description {the sign}
		sightAngle 40
		lookStr {You are in a quickie marriage parlor. A flashing neon sign on the rear wall asks: 
		"Why wait? Marry the girl of your dreams today! You provide the girl, we'll do the rest for only $100!!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pew1 of Feature
	(properties
		x 234
		y 144
		nsTop 117
		nsLeft 152
		nsBottom 172
		nsRight 317
		description {the pew}
		sightAngle 40
		lookStr {It seems everything here is covered in plastic.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 410 48))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pew2 of Feature
	(properties
		x 50
		y 156
		nsTop 124
		nsBottom 189
		nsRight 100
		description {the pew}
		sightAngle 40
		lookStr {It seems everything here is covered in plastic.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 410 48))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bouquet of Feature
	(properties
		x 118
		y 114
		z 58
		nsTop 41
		nsLeft 83
		nsBottom 72
		nsRight 153
		description {the bouquet}
		sightAngle 40
		lookStr {A lovely artificial bouquet resides about an artificial mantle above an artificial fireplace /altar.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 410 49)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance candelabra of Feature
	(properties
		x 72
		y 131
		nsTop 71
		nsLeft 59
		nsBottom 126
		nsRight 85
		description {the candelabra}
		sightAngle 40
		approachX 95
		approachY 134
		lookStr {If you don't look too closely, you can barely see the wires running up the inside of the PVC tubing.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 410 50)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance candelabra2 of Feature
	(properties
		x 179
		y 93
		nsTop 64
		nsLeft 170
		nsBottom 122
		nsRight 188
		description {the candelabra}
		sightAngle 40
		approachX 160
		approachY 130
		lookStr {If you don't look too closely, you can barely see the wires running up the inside of the PVC tubing.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 410 50)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
