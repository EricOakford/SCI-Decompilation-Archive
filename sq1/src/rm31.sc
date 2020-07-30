;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm31 0
)

(local
	[local0 8] = [114 160 133 169 157 177 171 183]
)
(instance rm31 of SQRoom
	(properties
		lookStr {This is another large chamber in the underground complex. Paths run along the top and bottom ledges.}
		picture 31
		east 30
		west 32
	)
	
	(method (init)
		(LoadMany VIEW 428 21 10)
		(LoadMany SOUND 449 425 450 426 451)
		(cond 
			((== prevRoomNum 32) (= style 12))
			((== prevRoomNum 30) (ego posn: 308 123) (= style 11))
			(else (= style 11))
		)
		(if (== currentFloor 1)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 0 319 1 319 44 254 43 207 48 203 57 189 55 118 60 0 60
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 0 73 197 65 256 56 319 50 319 189 0 189
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							319 189 0 189 28 180 117 147 152 157 163 156 186 161
							180 170 223 182 319 152
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0 319 0 319 100 232 108 216 132 173 143
							143 137 117 144 28 178 0 180
						yourself:
					)
			)
		)
		(grate init:)
		(frontOfGrate init:)
		(upperledge init:)
		(lowerLedge init:)
		(ego init:)
		(if (Btst fFlag72) (stickyPlant init:))
		(if (Btst fGrateMonsterAppears)
			(grateMonster1 init:)
			(grateMonster2 init:)
			(eyeStalk init: setScript: waveYourEyeStalk)
			(if (Btst fGrateMonsterStuck)
				(globalSound number: 449 loop: -1 play:)
				(grateMonster1 setScript: tentacleStuck)
			else
				(globalSound number: 425 loop: -1 play:)
				(tentacle1 init: hide:)
				(tentacle1 setScript: tentacleWave 0 0)
			)
		)
		(super init:)
		(HandsOn)
	)
	
	(method (doit)
		(cond 
			(script (script doit:))
			((& (ego onControl: 0) cCYAN) (curRoom newRoom: 34))
			(
			(and (not (Btst fGrateMonsterAppears)) (& (ego onControl: 0) cLMAGENTA))
				(globalSound number: 425 loop: -1 play:)
				(Bset fGrateMonsterAppears)
				(grateMonster1 init:)
				(grateMonster2 init:)
				(eyeStalk init: hide: setScript: waveYourEyeStalk)
				(tentacle1 init: hide:)
				(tentacle1 setScript: tentacleWave 0 0)
			)
			(
			(and (not (Btst fGrateMonsterStuck)) (& (ego onControl: origin) cYELLOW)) (HandsOff) (self setScript: monsterGrabsEgo))
			(
			(and (not (Btst fGrateMonsterStuck)) (& (ego onControl: origin) cLBLUE)) (HandsOff) (self setScript: monsterGrabsEgoSide))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 31 0)
			)
			(verbTalk
				(Print 31 1)
			)
			(verbSmell
				(Print 31 2)
			)
			(verbTaste
				(Print 31 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(globalSound fade:)
		(super newRoom: n)
	)
)

(instance tentacleWave of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (Btst fGrateMonsterStuck) (= state 4) else (= state register))
				(= cycles 3)
			)
			(1
				(= temp0 (* 2 (Random 0 3)))
				(tentacle1
					setLoop: 7
					show:
					x: [local0 temp0]
					y: [local0 (+ temp0 1)]
					cel: 0
					show:
					setCycle: EndLoop self
				)
				(= register 1)
				(= state -1)
			)
			(2
				(tentacle1
					setLoop: (Random 6 8)
					cel: 0
					setCycle: EndLoop self
				)
				(= register 2)
				(= state -1)
			)
			(3
				(tentacle1 setCycle: BegLoop self)
				(= register 3)
				(= state -1)
			)
			(4
				(tentacle1 setLoop: 7 cel: 1)
				(= register 0)
				(= cycles 3)
			)
			(5
				(tentacle1 setCycle: BegLoop self)
			)
			(6
				(tentacle1 setLoop: 7 cel: 0 setCycle: BegLoop self)
			)
			(7
				(tentacle1 hide:)
				(if (not (Btst fGrateMonsterStuck)) (= state -1))
				(= cycles 3)
			)
			(8
				(self setScript: tentacleGetsStuck self)
			)
			(9 (self dispose:))
		)
	)
)

(instance tentacleGetsStuck of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tentacle2
					init:
					show:
					x: 150
					y: 175
					setLoop: 9
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(1
				(globalSound number: 450 loop: 1 play: self)
			)
			(2
				(self setScript: tentacleStuck self)
			)
			(3 (self dispose:))
		)
	)
)

(instance tentacleStuck of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 449 loop: -1 play:)
				(tentacle2
					init:
					x: 150
					y: 175
					setLoop: 10
					cel: 0
					cycleSpeed: 8
					setCycle: Oscillate
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance waveYourEyeStalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eyeStalk hide: x: (Random 80 150) y: (Random 170 190))
				(= cycles (Random 60 180))
			)
			(1
				(eyeStalk show: setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(eyeStalk setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(eyeStalk setLoop: 3 cel: 0 setCycle: Oscillate 2 self)
			)
			(4
				(eyeStalk setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(5 (self init:))
		)
	)
)

(instance stickyPlant of Actor
	(properties
		x 211
		y 117
		description {sticky plant thing}
		lookStr {A piece of sticky plant is stuck to the floor.}
		yStep 13
		view 21
		loop 2
		priority 11
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 3
		xStep 13
		moveSpeed 3
	)
	
	(method (init)
		(self setCycle: EndLoop setMotion: MoveTo 171 163)
		(super init: &rest)
	)
)

(instance grateMonster1 of Actor
	(properties
		x 115
		y 200
		description {grate monster 1}
		sightAngle 45
		view 428
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
	
	(method (init)
		(self setCycle: Forward setLoop: 0 setMotion: MoveTo 115 189)
		(super init: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fGrateMonsterStuck)
					(Print 31 4)
				else
					(Print 31 5)
				)
			)
			(verbUse
				(grate doVerb: theVerb theItem &rest)
			)
			(verbSmell
				(Print 31 6)
			)
			(verbTaste
				(Print 31 7)
			)
			(verbDo
				(if (Btst fGrateMonsterStuck)
					(Print 31 8)
				else
					(Print 31 9)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance grateMonster2 of Actor
	(properties
		x 115
		y 200
		description {grate monster 2}
		sightAngle 45
		view 428
		cel 5
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
	
	(method (init)
		(self setCycle: Forward setLoop: 0 setMotion: MoveTo 115 189)
		(super init: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(grateMonster1 doVerb: theVerb theItem)
	)
)

(instance tentacle1 of Actor
	(properties
		x 126
		y 164
		description {tentacle 1}
		lookStr {Hey, this puppy has some keen-looking tentacles.}
		view 428
		loop 7
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
	
	(method (doVerb theVerb theItem)
		(grateMonster1 doVerb: theVerb theItem)
	)
)

(instance tentacle2 of Actor
	(properties
		x 161
		y 178
		description {tentacle 2}
		lookStr {Hey, this puppy has some keen-looking tentacles.}
		view 428
		loop 7
		signal (| fixPriOn fixedLoop)
		cycleSpeed 8
	)
	
	(method (doVerb theVerb theItem)
		(grateMonster1 doVerb: theVerb theItem)
	)
)

(instance eyeStalk of Prop
	(properties
		x 125
		y 179
		description {creature eyestalk}
		lookStr {It's got the cutest little grey eye you've ever seen.}
		view 428
		loop 1
		signal (| ignrAct fixedLoop)
		cycleSpeed 6
	)
	
	(method (doVerb theVerb theItem)
		(grateMonster1 doVerb: theVerb theItem)
	)
)

(instance upperledge of Feature
	(properties
		x 134
		y 55
		description {upper ledge}
		sightAngle 45
		onMeCheck NEARCHECK
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if (ego onControl: cBLUE)
					(Print 31 10)
				else
					(Print 31 11)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lowerLedge of Feature
	(properties
		x 156
		y 142
		description {lowerledge}
		sightAngle 45
		onMeCheck $2010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (ego onControl: 8208)
					(Print 31 12)
				else
					(Print 31 13)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance genericItem of Actor
	(properties
		x 205
		y 100
		view 810
		priority 5
		signal (| ignrAct fixPriOn)
		cycleSpeed 5
	)
)

(instance frontOfGrate of Feature
	(properties
		description {front of grate area}
		onMeCheck $4000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 31 14))
			(4
				(switch theItem
					(16
						(ego setScript: egoThrowsPlant)
					)
				)
			)
		)
	)
)

(instance grate of Feature
	(properties
		x 106
		y 181
		description {grate on floor}
		sightAngle 45
		onMeCheck FARCHECK
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fGrateMonsterAppears)
					(Print 31 15)
				else
					(Print 31 16)
				)
			)
			(verbSmell
				(Print 31 6)
			)
			(verbUse
				(switch theItem
					(iPlant
						(ego setScript: egoThrowsPlant)
					)
					(iBuckazoid
						(Print 31 17)
					)
					(iCartridge
						(Print 31 18)
					)
					(iWidget
						(Print 31 19)
					)
					(iGadget
						(Print 31 20)
					)
					(iKnife
						(Print 31 21)
					)
					(iDehydratedWater
						(Print 31 22)
					)
					(iOratPart
						(Print 31 23)
					)
					(else 
						(cond 
							((Btst fGrateMonsterStuck)
								(Print 31 24)
							)
							((Btst fGrateMonsterAppears)
								(if (== currentFloor 1)
									(Print 31 25)
								else
									(tentacle2 init: hide:)
									(tentacle2 setScript: egoThrowsWrongItem)
								)
							)
							(else
								(Print 31 26)
							)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance monsterGrabsEgoSide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: (ego loop?) setMotion: MoveTo 25 183 self)
				(tentacle2
					init:
					x: 61
					y: 189
					setPri: 14
					view: 21
					setLoop: 4
					cel: 0
					setCycle: CycleTo 1 1
				)
			)
			(1
				(tentacle2 setCycle: CycleTo 3 1 self)
			)
			(2
				(tentacle2 cel: 4)
				(ego hide:)
				(= cycles 1)
			)
			(3
				(tentacle2 setCycle: CycleTo 7 1 self)
			)
			(4
				(globalSound number: 366 loop: 1 play:)
				(tentacle2 cel: 8 setCycle: EndLoop self)
			)
			(5
				(globalSound number: 426 loop: 1 play:)
				(Print 31 27)
				(= seconds 4)
			)
			(6
				(Print 31 28)
				(EgoDead 943 0 0 31 29)
				(self dispose:)
			)
		)
	)
)

(instance monsterGrabsEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0 setLoop: 5)
				(= cycles 3)
			)
			(1
				(tentacle2
					init:
					view: 428
					setLoop: 7
					cel: 0
					show:
					x: (- (ego x?) 35)
					y: (+ (ego y?) 7)
					setCycle: EndLoop self
				)
			)
			(2
				(tentacle2
					view: 21
					setLoop: 3
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(3
				(ego hide:)
				(tentacle2 cel: 4 setCycle: CycleTo 7 1 self)
			)
			(4
				(globalSound number: 366 loop: 1 play:)
				(tentacle2 cel: 8 setCycle: EndLoop self)
			)
			(5
				(globalSound number: 426 loop: 1 play:)
				(Print 31 27)
				(= seconds 4)
			)
			(6
				(Print 31 28)
				(EgoDead 943 0 0 31 29)
			)
		)
	)
)

(instance egoThrowsWrongItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 250 138 self)
			)
			(1
				(Face ego grateMonster1)
				(= cycles 3)
			)
			(2
				(ego view: 21 setLoop: 0 cel: 0 setCycle: CycleTo 5 1 self)
				(self setScript: tentacleKnocksItBack)
			)
			(3 (= ticks 10))
			(4
				(genericItem
					init:
					setLoop: 5
					xStep: 6
					yStep: 6
					setCycle: Forward
					cycleSpeed: 0
					setMotion: MoveTo 176 139 self
				)
				(ego setCycle: EndLoop)
			)
			(5
				(genericItem setMotion: MoveTo 205 148 self)
			)
			(6
				(genericItem setMotion: MoveTo 210 140 self)
			)
			(7
				(genericItem setMotion: MoveTo 216 143 self)
			)
			(8
				(ego view: 10 setLoop: 5 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(9
				(genericItem dispose:)
				(ego setCycle: EndLoop self)
			)
			(10
				(Print 31 30)
				(Bset fGrateMonsterAppears)
				(NormalEgo 1 1 61)
			)
		)
	)
)

(instance tentacleKnocksItBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tentacle2
					show:
					cycleSpeed: 4
					x: 138
					y: 170
					view: 428
					setLoop: 6
					cel: 0
				)
				(= cycles 12)
			)
			(1
				(tentacle2 setCycle: EndLoop self)
			)
			(2
				(soundFx number: 451 loop: 1 play:)
				(tentacle2 setCycle: BegLoop self)
			)
			(3 (tentacle2 hide:))
		)
	)
)

(instance egoThrowsPlant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((Btst fGrateMonsterStuck) (Print 31 31) (self dispose:))
					((not (Btst fGrateMonsterAppears)) (Print 31 32) (self dispose:))
					(else (HandsOff) (ego setMotion: PolyPath 249 132 self))
				)
			)
			(1
				(Face ego grateMonster1)
				(= cycles 3)
			)
			(2
				(ego view: 21 setLoop: 0 cel: 0 setCycle: CycleTo 5 1 self)
			)
			(3
				(stickyPlant init:)
				(ego put: 16 31)
				(if (Btst fGrateMonsterAppears) (Bset fGrateMonsterStuck) (SolvePuzzle 5 fTrapGrateMonster))
				(ego setCycle: EndLoop self)
			)
			(4
				(NormalEgo 1 1 61)
				(= seconds 8)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)
