;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use DScript)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm32 0
)

(local
	local0
	local1
	local2
	theCel
	local4
	local5
)
(instance rm32 of SQRoom
	(properties
		picture 32
		east 31
		west 33
	)
	
	(method (init &tmp temp0)
		(LoadMany VIEW 22 132 946 610)
		(LoadMany SOUND 430 424 433 396 397 398 399)
		(= style (if (== prevRoomNum west) SCROLLLEFT else SCROLLRIGHT))
		(if (== prevRoomNum 4)
			(ego posn: 160 180)
			(= currentFloor 2)
		)
		(if (== currentFloor 2)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 183 160 183 244 168 257 185 258 187 319 187 319 189 0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 0 134 25 139 24 152 0 152
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							319 173 282 177 253 153 227 153 195 154 163 151 146 140
							121 133 74 126 48 125 43 132 30 136 12 134 6 123 0 121
							0 0 319 0
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 116 166 118 161 132 153 151 154 152 166 140 174 122 171
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 27 147 28 140 49 132 73 129 90 135 92 146 74 154 53 155 28 153
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 79 0 58 49 57 107 73 165 81 213 77 319 72 319 189 0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0 319 0 319 57 290 57 225 64 183 67 138 68
							112 66 100 62 56 48 0 48
						yourself:
					)
			)
		)
		(if (not (Btst fPutRockInHole))
			(Load SOUND 423)
			(ggyer init:)
			(geyser init: setCycle: Forward)
			(door init: ignoreActors: 0)
		else
			(plug init:)
		)
		(ego init: ignoreActors: 0)
		(= local2 0)
		(fallingAcid init: setScript: acidDoYourThing)
		(acidPool init:)
		(acidVapor init:)
		(lowerledge init:)
		(upperledge init:)
		(drop1 init: hide: setScript: drop1Falling)
		(drop2 init: hide: setScript: drop2Falling)
		(drop3 init: hide: setScript: drop3Falling)
		(drop4 init: hide: setScript: drop4Falling)
		(drop5 init: hide: setScript: drop5Falling)
		(drop6 init: hide: setScript: drop6Falling)
		(drop7 init: hide: setScript: drop7Falling)
		(drop8 init: hide: setScript: drop8Falling)
		(drop9 init: hide: setScript: drop9Falling)
		(super init:)
		(HandsOn)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local5 0)
			((and (== currentFloor 1) (> (ego x?) 202)) (= local5 1) (SolvePuzzle 3 fPassAcidDrops))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 32 0)
			)
			(verbTalk
				(if (Btst fPutRockInHole)
					(Print 32 1)
				else
					(Print 32 2)
				)
			)
			(verbSmell
				(Print 32 3)
			)
			(verbTaste
				(Print 32 4)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoTouchesAcid of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== currentFloor 1) (Print 32 5))
					((& (ego onControl: 0) cLMAGENTA) (HandsOff) (ego setMotion: PolyPath 170 153 self))
					(else (Print 32 5))
				)
			)
			(1
				(ego setMotion: MoveTo 174 152 self)
			)
			(2
				(ego
					view: 22
					cycleSpeed: 8
					setLoop: 9
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(3
				(soundFx number: 430 loop: 0 play:)
				(ego setCycle: CycleTo 8 1 self)
			)
			(4
				(soundFx number: 454 loop: 1 play: self)
			)
			(5 (ego setCycle: EndLoop self))
			(6
				(ego setLoop: 10 cel: 0 cycleSpeed: 8 setCycle: EndLoop self)
			)
			(7
				(= local4 1)
				(theMusic number: 472 loop: -1 play:)
				(viewBox init: stopUpd:)
				(heads init: stopUpd:)
				(mouth1 init:)
				(mouth2 init:)
				(= cycles 5)
			)
			(8
				(Print 32 6 #dispose #at 15 20)
				(mouth1 setCycle: RandCycle 15)
				(= seconds 3)
			)
			(9
				(Print 32 7 #dispose #at 15 20)
				(mouth2 setCycle: RandCycle 25)
				(= seconds 10)
			)
			(10
				(modelessDialog dispose:)
				(= cycles 20)
			)
			(11
				(mouth2 setCycle: RandCycle 20)
				(Print 32 8 #dispose #at 15 40)
				(= seconds 7)
			)
			(12
				(modelessDialog dispose:)
				(= cycles 10)
			)
			(13 (= seconds 4))
			(14 (= cycles 5))
			(15
				(mouth1 cycleSpeed: 6 setCycle: RandCycle 10)
				(mouth2 cycleSpeed: 6 setCycle: RandCycle 10)
				(= cycles 2)
			)
			(16
				(self
					save1:
						(Display 32 9
							p_mode teJustCenter
							p_font SYSFONT
							p_width 165
							p_at 78 40
							p_color myTextColor6
							p_save
						)
				)
				(= cycles 6)
			)
			(17
				(self restore:)
				(= cycles 1)
			)
			(18
				(self
					save1:
						(Display 32 9
							p_mode teJustCenter
							p_font SYSFONT
							p_width 165
							p_at 78 40
							p_color colLRed
							p_save
						)
				)
				(++ local1)
				(= cycles 6)
			)
			(19
				(self restore:)
				(= cycles 2)
				(if (< local1 4) (= state 15))
			)
			(20 (= cycles 10))
			(21
				(ego cycleSpeed: 8 setCycle: BegLoop self)
			)
			(22
				(ego
					cycleSpeed: 8
					setLoop: 9
					cel: 0
					setCycle: CycleTo 5 1 self
				)
				(mouth1 setCycle: RandCycle 15)
				(arrowThing init: setCycle: EndLoop)
				(= seconds 2)
			)
			(23 0)
			(24
				(Print 32 10 #dispose #at 15 40)
				(ego setCycle: CycleTo 8 1 self)
				(circleThing init: setCycle: EndLoop)
				(= seconds 4)
			)
			(25 0)
			(26 (ego cel: 7) (= cycles 10))
			(27
				(ego cycleSpeed: 8 setCycle: EndLoop self)
				(mouth1 setCycle: RandCycle 10)
				(circleThing dispose:)
				(arrowThing dispose:)
			)
			(28 (= seconds 2))
			(29
				(Print 32 11 #at 15 40 #dispose)
				(ego setLoop: 10 cel: 0 setCycle: EndLoop)
				(= seconds 5)
			)
			(30
				(modelessDialog dispose:)
				(Print 32 12)
				(= seconds 4)
			)
			(31
				(viewBox dispose:)
				(heads dispose:)
				(mouth1 dispose:)
				(mouth2 dispose:)
				(= cycles 20)
			)
			(32 (EgoDead 938 0 0 32 13))
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local0 0)
	)
)

(instance egoPlugsGeyser of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 270 189 self)
			)
			(1 (Face ego geyser self))
			(2
				(ego view: 22 setLoop: 5 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(3
				(soundFx number: 424 loop: 0 play:)
				(plug init:)
				(geyser dispose:)
				(ggyer dispose:)
				(SolvePuzzle 4 fPlugGeyser)
				(ego put: 7 32)
				(Bset fPutRockInHole)
				(ego setCycle: EndLoop self)
			)
			(4
				(soundFx number: 433 loop: 1 play:)
				(door ignoreActors: 1 cycleSpeed: 10 setCycle: EndLoop self)
			)
			(5
				(soundFx stop:)
				(NormalEgo 1 1 61)
				(ego setMotion: MoveTo 270 185 self)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance circleThing of Prop
	(properties
		x 186
		y 123
		view 610
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
)

(instance arrowThing of Prop
	(properties
		x 160
		y 134
		view 610
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
)

(instance viewBox of View
	(properties
		x 227
		y 16
		view 946
		loop 3
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance heads of View
	(properties
		x 226
		y 8
		view 946
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance mouth1 of Prop
	(properties
		x 250
		y 36
		view 946
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 5
	)
)

(instance mouth2 of Prop
	(properties
		x 271
		y 39
		view 946
		loop 2
		cel 2
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 5
	)
)

(instance helmet of View
	(properties
		x 170
		y 153
		description {a spacesuit helmet}
		view 22
		loop 6
		signal ignrAct
	)
)

(instance ggyer of Feature
	(properties
		x 245
		y 172
		description {geyser}
		onMeCheck $0200
		approachX 270
		approachY 187
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbUse)
	)
	
	(method (doVerb)
		(geyser doVerb: &rest)
	)
)

(instance geyser of Prop
	(properties
		x 246
		y 187
		z 15
		description {geyser}
		onMeCheck $0200
		approachX 270
		approachY 187
		view 132
		loop 5
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbUse)
	)
	
	(method (doit)
		(super doit:)
		(if (and (== cel 2) (!= theCel cel))
			(geyserSound loop: 0 play:)
		)
		(= theCel cel)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fPutRockInHole)
					(Print 32 14)
				else
					(Print 32 15)
				)
			)
			(verbDo
				(Print 32 16)
			)
			(verbTaste
				(Print 32 17)
			)
			(verbSmell
				(Print 32 18)
			)
			(verbTalk
				(Print 32 19)
			)
			(verbUse
				(switch theItem
					(iRock
						(curRoom setScript: egoPlugsGeyser)
					)
					(iKnife
						(Print 32 20)
					)
					(iBrokenGlass
						(Print 32 21)
					)
					(else
						(Print 32 22)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plug of View
	(properties
		x 244
		y 173
		view 132
		loop 3
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 32 23)
			)
			(verbDo
				(Print 32 24)
			)
			(verbTalk
				(Print 32 25)
			)
			(verbTaste
				(Print 32 24)
			)
			(verbSmell
				(Print 32 26)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance upperledge of Feature
	(properties
		x 154
		y 40
		onMeCheck $0806
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (== currentFloor 1)
					(Print 32 27)
				else
					(Print 32 28)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lowerledge of Feature
	(properties
		x 159
		y 137
		onMeCheck $2000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((& (ego onControl: 0) cYELLOW)
						(if (== (door cel?) 0)
							(Print 32 29)
						else
							(Print 32 30)
						)
					)
					((== currentFloor 2) (Print 32 31))
					(else
						(Print 32 32)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 272
		y 177
		view 132
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 32 33)
			)
			(verbDo
				(Print 32 34)
			)
			(verbTalk
				(Print 32 35)
			)
			(verbSmell
				(Print 32 36)
			)
			(verbTaste
				(Print 32 37)
			)
			(verbUse
				(Print 32 38)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance acidPool of Feature
	(properties
		x 197
		y 133
		description {acid pool}
		sightAngle 45
		onMeCheck $0400
		lookStr {acid Pool}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((& (ego onControl:) cYELLOW) (Print 32 5))
					((& (ego onControl:) cLMAGENTA) (Print 32 39))
					((== currentFloor 1) (Print 32 40))
					(else (VerbFail))
				)
			)
			(verbDo
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (ego setScript: egoTouchesAcid))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(verbSmell
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (ego setScript: egoTasteAcid 0 2))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(verbTaste
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (ego setScript: egoTasteAcid 0 1))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(verbTalk
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (Print 32 41))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(verbUse
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (Print 32 42))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance acidDoYourThing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fallingAcid show: cel: 0 setCycle: CycleTo 3 1 self)
			)
			(1
				(if (not local4)
					(poolDrop
						loop: 0
						play: (if (== currentFloor 1) 70 else 127)
					)
				)
				(acidVapor show: cel: 0 setCycle: Forward)
				(fallingAcid setCycle: EndLoop self)
			)
			(2
				(fallingAcid hide:)
				(= cycles 30)
			)
			(3
				(acidVapor hide:)
				(= cycles (Random 30 120))
			)
			(4 (self init:))
		)
	)
)

(instance fallingAcid of Actor
	(properties
		x 195
		y 135
		view 132
		loop 2
		cycleSpeed 4
		moveSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (Print 32 43))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(else 
				(acidPool doVerb: theVerb &rest)
			)
		)
	)
)

(instance acidVapor of Prop
	(properties
		x 194
		y 130
		view 132
		loop 4
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((& (ego onControl:) cYELLOW) (Print {You can't do that from here.}))
					((& (ego onControl:) cLMAGENTA) (Print 32 44))
					((== currentFloor 1) (Print {You can't do that from here.}))
				)
			)
			(else 
				(acidPool doVerb: theVerb &rest)
			)
		)
	)
)

(instance poolDrop of Sound
	(properties
		number 396
	)
)

(instance geyserSound of Sound
	(properties
		number 423
	)
)

(instance dripSound of Sound
	(properties)
)

(class AcidDrop of Actor
	(properties
		description {a small drop of green liquid}
		yStep 3
		view 132
		loop 1
		cel 0
		signal (| ignrAct fixedLoop)
		ctrlColor 0
		theScript 0
		tarY 0
	)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			(
				(and
					(self
						inRect:
							(+ (ego nsLeft?) 3)
							(ego nsTop?)
							(- (ego nsRight?) 3)
							(- (ego nsBottom?) 3)
					)
					(& (ego onControl: origin) ctrlColor)
					(== local2 0)
				)
				(ego setScript: egoHitByAcidDrop)
				(= local2 1)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 32 45)
			)
			(verbDo
				(Print 32 46)
			)
			(verbSmell
				(Print 32 47)
			)
			(verbTaste
				(Print 32 48)
			)
			(verbTalk
				(Print 32 49)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drop1 of AcidDrop
	(properties
		x 115
		tarY 72
	)
)

(instance drop2 of AcidDrop
	(properties
		x 124
		tarY 69
	)
)

(instance drop3 of AcidDrop
	(properties
		x 132
		tarY 70
	)
)

(instance drop4 of AcidDrop
	(properties
		x 141
		tarY 70
	)
)

(instance drop5 of AcidDrop
	(properties
		x 157
		tarY 76
	)
)

(instance drop6 of AcidDrop
	(properties
		x 163
		tarY 70
	)
)

(instance drop7 of AcidDrop
	(properties
		x 175
		tarY 78
	)
)

(instance drop8 of AcidDrop
	(properties
		x 189
		tarY 77
	)
)

(instance drop9 of AcidDrop
	(properties
		x 196
		tarY 69
	)
)

(instance egoHitByAcidDrop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 1)
				(= cycles 1)
			)
			(1
				(soundFx number: 430 loop: 1 play:)
				(theMusic2 number: 455 loop: 1 play:)
				(ego
					view: 22
					setLoop: (if (OneOf (ego loop?) 1 5 7) 8 else 7)
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(2
				(Print 32 50 #at 50 100)
				(= cycles 3)
			)
			(3 (EgoDead 938 0 0 32 51))
		)
	)
)

(instance drop1Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop2Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop3Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop4Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop5Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop6Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop7Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop8Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance drop9Falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 15 60)))
			(1
				(if (< (client tarY?) 73) (client ctrlColor: 4))
				(client
					show:
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (client x?) 20 self
				)
			)
			(2
				(client
					setMotion: MoveTo (client x?) (client tarY?) self
				)
				(if (> (client tarY?) 72) (client setPri: 12))
			)
			(3
				(if (not local4)
					(dripSound
						number: (Random 397 399)
						loop: 1
						play: (if (== currentFloor 1) 127 else 50)
					)
				)
				(client setLoop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(client hide: y: 0 setPri: 1)
				(= cycles 3)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance egoTasteAcid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== currentFloor 1) (Print {You can't do that from here.}) (self dispose:))
					((& (ego onControl: 0) cLMAGENTA) (HandsOff) (ego setMotion: PolyPath 170 153 self))
					(else (Print {You can't do that from here.}))
				)
			)
			(1
				(ego view: 22 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(helmet init:)
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(switch register
					(1 (Print 32 52 #at 50 10))
					(2 (Print 32 53 #at 50 10))
				)
				(= cycles 9)
			)
			(4
				(soundFx number: 430 loop: 0 play:)
				(ego setLoop: 2 cel: 0 cycleSpeed: 8 setCycle: EndLoop self)
			)
			(5
				(ego setLoop: 11 cel: 0 setCycle: Oscillate 1 self)
			)
			(6
				(ego setLoop: 12 cel: 0 setCycle: EndLoop self)
			)
			(7 (EgoDead 938 0 0 32 54))
		)
	)
)
