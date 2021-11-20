;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include sci.sh)
(use Main)
(use GloryRm)
(use EgoDead)
(use Print)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm740 0
	torchEff 1
	doRitual 2
)

(local
	local0
	gTheObj_2CycleSpeed
	gTheObj_2MoveSpeed
	newAcidBlood
	newAcidBlood2
	local5
	newBloodProp
	newBloodProp_2
	newBloodProp_3
	newBloodProp_4
	newBloodProp_5
	newBloodProp_6
	local12
	local13
	local14
	local15
	gTheObj_2Z
	newProp
	local18
	local19
	local20
	[local21 2]
	local23
	theGGGTheObj_2CycleSpeed
	local25
)
(instance rm740 of GloryRm
	(properties
		noun 1
		picture 740
	)
	
	(method (init)
		(Bclr 6)
		(fLowerHold init: approachVerbs: 33 10)
		(fLowerPath init:)
		(fUpperPath init:)
		(altarHead init:)
		(altarBowl init:)
		(altarBase init:)
		(island init:)
		(upperShelf init:)
		(lowerShelf init:)
		(bridge init:)
		(bridgeLedge init:)
		(spoutPillar init:)
		(roomExit init:)
		(rock init:)
		(littleLedge init:)
		(lowLedge init:)
		(fSteps init:)
		(walkHandler addToFront: fSteps)
		(theMusic number: 200 setLoop: -1 play:)
		(super init: &rest)
		(RemapColors 2 254 60)
		(RemapColors 1 253 112 175 62)
		(Bset 373)
		(ego
			init:
			x: 75
			y: 43
			normalize:
			changeGait:
			setScaler: Scaler 77 37 175 47
		)
		(curRoom addObstacle: (roomPoly init: yourself:))
		(Palette palSET_FLAG 66 85 local0)
		(torchEff init: setScaler: ego setCycle: RandCycle)
		(aRock init:)
		(= local13 0)
		(self setScript: (ScriptID 741 1))
		(theGame save: 1)
	)
	
	(method (doit)
		(if
			(and
				(== (curRoom script?) (ScriptID 31 1))
				(>= (ego z?) 25)
			)
			(= gTheObj_2Z (ego z?))
			(curRoom setScript: sLevitating)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: fSteps)
		(theMusic fade: 0)
		(if script (script dispose:))
		(DisposeScript 741)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(11
					(if (Btst 340)
						(messager say: 16 11 9)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(89
					(switch local13
						(0
							((ScriptID 31 0) init: 96 167 30 0 2)
						)
						(1
							(if (> (ego x?) 135)
								(messager say: 0 89 0 0)
								(return 1)
							else
								(curRoom setScript: sLowerLevitate)
							)
						)
						(5
							(curRoom setScript: sAltarLevitate)
						)
						(4
							(curRoom setScript: sAltarLevitate)
						)
						(else 
							(messager say: 0 89 0 0)
							(return 1)
						)
					)
				)
				(1
					(if (Btst 340)
						(messager say: 1 1 2)
					else
						(messager say: 1 1 1)
					)
					(return 1)
				)
				(56
					(messager say: 0 56 0 0)
					(return 1)
				)
				(47
					(messager say: 0 47 0 0)
					(return 1)
				)
				(14
					(messager say: 0 14 0 0)
					(return 1)
				)
				(67
					(messager say: 0 67 0 0)
					(return 1)
				)
				(69
					(messager say: 0 69 0 0)
					(return 1)
				)
				(70
					(messager say: 0 70 0 0)
					(return 1)
				)
				(179
					(messager say: 0 179 0 0)
					(return 1)
				)
				(33
					(messager say: 0 33 0 0)
					(return 1)
				)
				(74
					(messager say: 0 74 0 0)
					(return 1)
				)
				(72
					(messager say: 0 72 0 0)
					(return 1)
				)
				(10
					(if (> [egoStats 2] 0)
						(if (== local13 0)
							(messager say: 0 159 23)
						else
							(messager say: 0 159 0 0)
						)
					else
						(messager say: 5 1 0 0)
					)
					(return 1)
				)
				(60
					(messager say: 0 60 0 0)
					(return 1)
				)
				(104
					(messager say: 0 104 0 0)
					(return 1)
				)
				(81
					(messager say: 0 81 0 0)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sAltarLevitate of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 2)
			(cond 
				((== (ego z?) 0) (self cue:))
				((== local13 4) (ego z: (+ (ego z?) 1)) (newProp z: (ego z?)))
				(else (ego z: (- (ego z?) 1)) (newProp z: (ego z?)))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local13 4)
					(ego setMotion: PolyPath 233 51 self)
				else
					(ego setMotion: PolyPath 230 76 self)
				)
			)
			(1
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				((= newProp (Prop new:))
					view: 17
					setLoop: 4 1
					x: (ego x?)
					y: (if (== local13 4)
						(+ (ego y?) 25)
					else
						(+ (ego y?) 1)
					)
					setCycle: Fwd
					setScaler:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					priority: (ego priority?)
					signal: 16385
					init:
				)
				(= ticks 6)
			)
			(2
				(if (== local13 4)
					(ego setLoop: 2 setMotion: JumpTo 230 76 self)
				else
					(ego setMotion: JumpTo 233 51 self)
				)
			)
			(3 0)
			(4
				(ego trySkill: 29)
				(newProp hide: dispose:)
				(if (== local13 4)
					(= local13 5)
					(curRoom addObstacle: (altarPoly init: yourself:))
				else
					(= local13 4)
					(curRoom addObstacle: (lowerLedgePoly init: yourself:))
				)
				(ego normalize:)
				(theMusic2 stop:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLevitating of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= local13 1)
				(ego
					posn: (ego x?) (ego y?) gTheObj_2Z
					setMotion: JumpTo 107 145 self
				)
			)
			(1
				(ego normalize:)
				(curRoom addObstacle: (lowerPondPoly init: yourself:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLowerLevitate of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 3)
			(if (== (ego z?) 0)
				(self cue:)
			else
				(ego z: (- (ego z?) 1))
				(newProp z: (ego z?))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 107 145 self)
			)
			(1
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego posn: 107 145)
				((= newProp (Prop new:))
					view: 17
					setLoop: 4 1
					x: (ego x?)
					y: (+ (ego y?) 1)
					setCycle: Fwd
					setScaler:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					priority: (ego priority?)
					signal: 16385
					init:
				)
				(= ticks 6)
			)
			(2
				(ego setMotion: JumpTo 96 167 self)
			)
			(3 0)
			(4
				(ego trySkill: 29)
				(newProp hide: dispose:)
				(= local13 0)
				(curRoom addObstacle: (roomPoly init: yourself:))
				(ego normalize:)
				(theMusic2 stop:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doRitual of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego use: 53)
				(sndDarkRumble play:)
				(theMusic mute: 0 5)
				(= ticks 280)
			)
			(1
				(if (not (Btst 340))
					(messager say: 2 69 0 0 self)
				else
					(self cue:)
				)
			)
			(2
				(sndWater play:)
				(= newAcidBlood (acidBlood new:))
				(newAcidBlood
					view: 740
					x: 261
					y: 94
					z: 40
					setLoop: 0 1
					cel: 0
					setPri: 83
					init:
					setCycle: End self
				)
			)
			(3
				(newAcidBlood setLoop: 1 1 setCycle: Fwd)
				(= ticks 15)
			)
			(4
				(= newAcidBlood2 (acidBlood2 new:))
				(newAcidBlood2
					view: 740
					x: 221
					y: 97
					setLoop: 2 1
					cel: 0
					init:
					setCycle: End self
				)
			)
			(5
				(newAcidBlood2 setLoop: 3 1 setCycle: Fwd)
				(= ticks 15)
			)
			(6
				(= newBloodProp (bloodProp new:))
				(newBloodProp
					view: 740
					x: 261
					y: 156
					signal: 20481
					noun: 17
					setLoop: 6 1
					cel: 0
					init:
					setCycle: End self
				)
			)
			(7
				(newBloodProp setLoop: 7 1 setCycle: Fwd)
				(= ticks 30)
			)
			(8
				(= newBloodProp_2 (bloodProp new:))
				(= newBloodProp_3 (bloodProp new:))
				(= newBloodProp_4 (bloodProp new:))
				(= newBloodProp_5 (bloodProp new:))
				(= newBloodProp_6 (bloodProp new:))
				(newBloodProp_2
					view: 740
					x: 141
					y: 2
					signal: 20481
					noun: 17
					setLoop: 8 1
					cel: 0
					init:
					setCycle: End self
				)
				(newBloodProp_3
					view: 740
					x: 23
					y: 60
					signal: 20481
					noun: 17
					setLoop: 13 1
					cel: 0
					init:
					setCycle: End
				)
				(newBloodProp_5
					view: 740
					x: 203
					y: 153
					setPri: 180
					signal: 20481
					noun: 17
					setLoop: 4 1
					cel: 0
					init:
					setCycle: End
				)
			)
			(9
				(newBloodProp_2 setLoop: 9 1 setCycle: Fwd)
				(newBloodProp_3 setLoop: 14 1 setCycle: Fwd)
				(newBloodProp_4
					view: 744
					setLoop: 5 1
					setCel: 0
					noun: 17
					posn: 266 178
					signal: 20481
					init:
					setCycle: Fwd
				)
				(newBloodProp_6
					view: 744
					setLoop: 11 1
					setCel: 0
					noun: 17
					posn: 168 152
					signal: 20481
					init:
					setCycle: Fwd
				)
				(newBloodProp_5 setLoop: 5 1 setCycle: Fwd)
				(= ticks 30)
			)
			(10
				(if (not (Btst 340))
					(messager say: 16 6 7 0 self)
					(Bset 340)
				else
					(self cue:)
				)
			)
			(11
				(ego solvePuzzle: 455 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPullsUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local13 0)
					(ego setMotion: PolyPath 96 167 self)
				else
					(ego setMotion: PolyPath 109 146 self)
				)
			)
			(1
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego view: 7 setLoop: 4 1 setCel: 0 posn: 109 146)
				(if (== local13 0)
					(ego setCycle: End self)
				else
					(ego setCycle: Beg self)
				)
			)
			(2
				(if (== local13 0)
					(curRoom addObstacle: (lowerPondPoly init: yourself:))
					(= local13 1)
				else
					(curRoom addObstacle: (roomPoly init: yourself:))
					(ego posn: 100 167)
					(= local13 0)
				)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sJumpThere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 15)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(cond 
					((and (== local13 5) (> (ego y?) 74)) (ego setMotion: PolyPath 235 74 self))
					((== local13 0) (messager say: 5 159 4 0 self))
					((and (== local13 3) local14) (messager say: 5 159 4 0 self))
					(else (self cue:))
				)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
			)
			(1
				(ego view: 30 setLoop: 3 1 setCel: 0 setSpeed: 6)
				(switch local13
					(0
						(ego setCycle: CT 8 1 setMotion: JumpTo 172 41 self)
					)
					(2
						(ego setCycle: CT 8 1 setMotion: JumpTo 146 50 self)
					)
					(3
						(if local14
							(ego setCycle: CT 8 1 setMotion: JumpTo 172 41 self)
						else
							(ego setCycle: CT 8 1 setMotion: JumpTo 233 51 self)
						)
					)
					(4
						(if local15
							(ego setCycle: CT 8 1 setMotion: JumpTo 208 26 self)
						else
							(ego setCycle: CT 8 1 setMotion: JumpTo 235 74 self)
						)
					)
					(5
						(ego setCycle: CT 8 1 setMotion: JumpTo 233 51 self)
					)
					(1
						(ego setCycle: CT 8 1 setMotion: JumpTo 96 167 self)
					)
				)
			)
			(2
				(ego normalize:)
				(switch local13
					(0
						(= local13 2)
						(curRoom addObstacle: (islandPoly init: yourself:))
					)
					(2
						(= local13 0)
						(curRoom addObstacle: (roomPoly init: yourself:))
					)
					(3
						(if local14
							(= local14 0)
							(= local13 2)
							(curRoom addObstacle: (islandPoly init: yourself:))
						else
							(= local13 4)
							(curRoom addObstacle: (lowerLedgePoly init: yourself:))
						)
					)
					(4
						(if local15
							(= local15 0)
							(= local13 3)
							(curRoom addObstacle: (highLedgePoly init: yourself:))
						else
							(= local13 5)
							(curRoom addObstacle: (altarPoly init: yourself:))
						)
					)
					(5
						(= local13 4)
						(curRoom addObstacle: (lowerLedgePoly init: yourself:))
					)
					(1
						(= local13 0)
						(curRoom addObstacle: (roomPoly init: yourself:))
					)
				)
				(= ticks 6)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoYouWanta of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 15)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(messager say: 6 159 3 1 self)
			)
			(1
				(theGame handsOn:)
				(if
					(= temp0
						(Print
							addText: 6 159 3 2 0 0 740
							addButton: 1 16 6 17 1 20 55 740
							addButton: 0 16 6 18 1 100 55 740
							init:
						)
					)
					(self cue:)
				else
					(self setScript: sJumpThere)
				)
			)
			(2
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(if (== (ego trySkill: 2 300) 1)
					(ego
						view: 30
						setLoop: 3 1
						setCel: 0
						setSpeed: 6
						setCycle: CT 8 1
						setMotion: JumpTo 208 26 self
					)
				else
					(self setScript: (ScriptID 741 2))
				)
			)
			(3
				(ego normalize:)
				(= local13 3)
				(curRoom addObstacle: (highLedgePoly init: yourself:))
				(= ticks 6)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoOnUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(if (< (ego y?) 130)
					(= local18 1)
					(ego setMotion: PolyPath 225 118 self)
				else
					(= local18 0)
					(ego setMotion: PolyPath 210 155 self)
				)
			)
			(1
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(if local18
					(ego
						changeGait: 1
						moveSpeed: 6
						cycleSpeed: 6
						setLoop: 0 1
						setMotion: MoveTo 247 118 self
					)
				else
					(ego
						changeGait: 1
						moveSpeed: 6
						cycleSpeed: 6
						setMotion: MoveTo 222 146 self
					)
				)
			)
			(2
				(if local18
					(self cue:)
				else
					(ego setMotion: MoveTo 247 140 self)
				)
			)
			(3
				(if local18
					(self cue:)
				else
					(ego setMotion: MoveTo 257 129 self)
				)
			)
			(4
				(ego setMotion: MoveTo 271 119 self)
			)
			(5
				(ego setMotion: MoveTo 293 113 self)
			)
			(6
				(ego setLoop: 1 1 setMotion: MoveTo 269 104 self)
			)
			(7
				(ego setLoop: 0 1 setMotion: MoveTo 289 96 self)
			)
			(8
				(ego setLoop: 1 1 setMotion: MoveTo 280 85 self)
			)
			(9
				(ego
					moveSpeed: gTheObj_2MoveSpeed
					cycleSpeed: gTheObj_2CycleSpeed
					changeGait: theGGGTheObj_2CycleSpeed
					normalize:
					setMotion: MoveTo 244 87 self
				)
			)
			(10
				(curRoom addObstacle: (altarPoly init: yourself:))
				(= local13 5)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(ego setMotion: PolyPath 280 85 self)
			)
			(1
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(ego
					changeGait: 1
					moveSpeed: 6
					cycleSpeed: 6
					setLoop: 0 1
					setMotion: MoveTo 289 96 self
				)
			)
			(2
				(ego setLoop: 1 1 setMotion: MoveTo 269 104 self)
			)
			(3
				(ego setLoop: 0 1 setMotion: MoveTo 293 113 self)
			)
			(4
				(ego setLoop: 1 1 setMotion: MoveTo 271 119 self)
			)
			(5
				(if local18
					(self cue:)
				else
					(ego setMotion: MoveTo 257 129 self)
				)
			)
			(6
				(if local18
					(self cue:)
				else
					(ego setMotion: MoveTo 247 140 self)
				)
			)
			(7
				(if local18
					(ego setMotion: MoveTo 247 118 self)
				else
					(ego setMotion: MoveTo 222 146 self)
				)
			)
			(8
				(if local18
					(ego setMotion: MoveTo 225 118 self)
				else
					(ego setMotion: MoveTo 210 155 self)
				)
			)
			(9
				(curRoom addObstacle: (lowerPondPoly init: yourself:))
				(if local18
					(ego
						moveSpeed: gTheObj_2MoveSpeed
						cycleSpeed: gTheObj_2CycleSpeed
						changeGait: theGGGTheObj_2CycleSpeed
						normalize:
						setMotion: PolyPath (- (ego x?) 2) 118 self
					)
				else
					(ego
						moveSpeed: gTheObj_2MoveSpeed
						cycleSpeed: gTheObj_2CycleSpeed
						changeGait: theGGGTheObj_2CycleSpeed
						normalize:
						setMotion: PolyPath (- (ego x?) 2) 155 self
					)
				)
			)
			(10
				(ego normalize:)
				(if
					(and
						(Btst 340)
						(not local23)
						(== (acidBlood2 script?) sLowerTimer)
					)
					(messager say: 16 6 20 0 self)
				else
					(self cue:)
				)
			)
			(11
				(= local13 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local19
					(0
						(self setScript: (ScriptID 32) self 21)
					)
					(1
						(if (== (ego has: 5) 1)
							(messager say: 16 6 25)
						else
							(ego use: 5)
							(self setScript: (ScriptID 32) self 37)
						)
					)
					(2
						(self setScript: (ScriptID 32) self 86)
					)
					(3
						(self setScript: (ScriptID 32) self 88)
					)
					(5
						(self setScript: (ScriptID 32) self 93)
					)
					(4
						(self setScript: (ScriptID 32) self 79)
					)
				)
			)
			(1 (= seconds 3))
			(2
				(switch local19
					(0
						(messager say: 2 21 0 0 self)
					)
					(1
						(messager say: 2 37 0 0 self)
					)
					(2
						(messager say: 2 86 0 0 self)
					)
					(3
						(messager say: 2 88 0 0 self)
					)
					(5
						(messager say: 2 93 0 0 self)
					)
					(4
						(messager say: 2 79 0 0 self)
					)
				)
			)
			(3
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowTheGrapnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 10)
				(ego trySkill: 11)
				(ego setMotion: PolyPath 207 80 self)
			)
			(1
				(ego
					view: 8
					posn: 213 80
					setLoop: 2 1
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(pGrapnel init: setCycle: End self)
				(ego setLoop: 7 1 setCel: 0 posn: 212 79 setCycle: End)
			)
			(3
				(ego normalize: setMotion: PolyPath 229 77 self)
			)
			(4
				(ego view: 7 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(5
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= local13 4)
				(ego
					setLoop: 4 1
					setCel: 0
					posn: 224 49
					setCycle: End self
				)
			)
			(6
				(curRoom addObstacle: (lowerLedgePoly init: yourself:))
				(ego normalize: setMotion: PolyPath 235 49 self)
			)
			(7
				(ego view: 4 setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(8
				(pGrapnel hide: dispose:)
				(= ticks 60)
			)
			(9 (ego setCycle: Beg self))
			(10
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= local13 5)
				(ego trySkill: 11)
				(ego setMotion: PolyPath 224 49 self)
			)
			(1
				(ego view: 7 setLoop: 4 1 setCel: 11 setCycle: Beg self)
			)
			(2
				(pGrapnel hide: dispose:)
				(curRoom addObstacle: (altarPoly init: yourself:))
				(ego posn: 222 77 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPushRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 209 47 self)
			)
			(1
				(ego trySkill: 0)
				(= local23 1)
				(Face ego (aRock x?) (aRock y?) self)
			)
			(2
				(aRock
					setStep: 10 10
					ignoreActors: 1
					setMotion: MoveTo 218 90 self
				)
			)
			(3
				(aRock setPri: 75 setMotion: MoveTo 218 98 self)
			)
			(4
				(aRock hide: dispose:)
				(newAcidBlood2 view: 744 setLoop: 1 1 setCycle: End)
				(= seconds 1)
			)
			(5
				(newBloodProp view: 744 setLoop: 3 1 setCycle: End)
				(newBloodProp_4 setLoop: 6 1 setCycle: End)
				(newBloodProp_5 setLoop: 5 setCycle: End)
				(newBloodProp_6 setLoop: 12 1 setCycle: End)
				(= seconds 1)
			)
			(6
				(newBloodProp hide: dispose:)
				(newBloodProp_4 hide: dispose:)
				(newBloodProp_5 hide: dispose:)
				(newBloodProp_6 hide: dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpperTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 9)
				(if local12
					(ego setScript: (ScriptID 32 0) self 86)
				else
					(= local25 1)
					(ego setScript: (ScriptID 32 0) self 79)
				)
			)
			(1 (= seconds 4))
			(2
				(if local12
					(messager say: 17 86 0 0 self)
				else
					(= local20 1)
					(newAcidBlood
						setCycle: 0
						signal: (& (newAcidBlood signal?) $fffe)
					)
					(newAcidBlood2
						setCycle: 0
						signal: (& (newAcidBlood2 signal?) $fffe)
					)
					(newBloodProp
						setCycle: 0
						signal: (& (newBloodProp signal?) $fffe)
					)
					(newBloodProp_4
						setCycle: 0
						signal: (& (newBloodProp_4 signal?) $fffe)
					)
					(newBloodProp_5
						setCycle: 0
						signal: (& (newBloodProp_5 signal?) $fffe)
					)
					(newBloodProp_6
						setCycle: 0
						signal: (& (newBloodProp_6 signal?) $fffe)
					)
					(self cue:)
				)
			)
			(3
				(if local12
					(= local12 0)
					(self dispose:)
				else
					(messager say: 16 6 11 0 self)
				)
			)
			(4
				(theGame handsOn:)
				(Bclr 9)
				(= seconds 20)
			)
			(5
				(= local20 0)
				(newAcidBlood
					setCycle: Fwd
					signal: (| (newAcidBlood signal?) $0001)
				)
				(= ticks 6)
			)
			(6
				(messager say: 16 6 12 0 self)
			)
			(7
				(messager say: 16 6 21 0 self)
			)
			(8
				(= local25 0)
				(acidBlood2 setScript: 0)
				(newAcidBlood2
					setCycle: Fwd
					signal: (| (newAcidBlood2 signal?) $0001)
				)
				(newBloodProp
					show:
					setCycle: Fwd
					signal: (| (newBloodProp signal?) $0001)
				)
				(newBloodProp_4
					setCycle: Fwd
					signal: (| (newBloodProp_4 signal?) $0001)
				)
				(newBloodProp_5
					setCycle: Fwd
					signal: (| (newBloodProp_5 signal?) $0001)
				)
				(newBloodProp_6
					setCycle: Fwd
					signal: (| (newBloodProp_6 signal?) $0001)
				)
				(= ticks 6)
			)
			(9 (self dispose:))
		)
	)
)

(instance sBurnedUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (not (Btst 340)) local23 local25)
					(self dispose:)
				else
					(theGame handsOff:)
					(ego setPri: 0 setMotion: 0)
					(= ticks 30)
				)
			)
			(1 (EgoDead 8 0 960 1))
		)
	)
)

(instance sLowerTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 9)
				(if local12
					(ego setScript: (ScriptID 32 0) self 86)
				else
					(= local25 1)
					(ego setScript: (ScriptID 32 0) self 79)
				)
			)
			(1 (= seconds 4))
			(2
				(if local12
					(messager say: 17 86 0 0 self)
				else
					(acidBlood2 setCycle: 0)
					(newBloodProp hide: setCycle: 0)
					(newBloodProp_6 hide:)
					(newBloodProp_5 hide:)
					(newBloodProp_4 hide:)
					(self cue:)
				)
			)
			(3
				(cond 
					(local12 (= local12 0) (self dispose:))
					((not local20) (messager say: 16 6 21 0 self))
					(else (messager say: 16 6 11 0 self))
				)
			)
			(4
				(theGame handsOn:)
				(Bclr 9)
				(if (not local20) (= seconds 1) else (= seconds 15))
			)
			(5
				(newAcidBlood2 setCycle: Fwd)
				(newBloodProp show: setCycle: Fwd)
				(= local25 0)
				(newBloodProp_6 show:)
				(newBloodProp_5 show:)
				(newBloodProp_4 show:)
				(= ticks 6)
			)
			(6
				(if local20
					(messager say: 16 6 12 0 self)
				else
					(self cue:)
				)
			)
			(7 (self dispose:))
		)
	)
)

(instance aRock of Actor
	(properties
		noun 12
		x 209
		y 57
		z 10
		view 741
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(if (== local13 5)
					(curRoom setScript: sThrowTheGrapnel)
				else
					(messager say: 7 159 3)
				)
			)
			(4
				(if (== local13 4)
					(if (Btst 340)
						(switch heroType
							(0
								(curRoom setScript: sPushRock)
							)
							(3
								(curRoom setScript: sPushRock)
							)
							(else  (messager say: 12 4 0 0))
						)
					else
						(messager say: 16 6 22)
					)
				else
					(messager say: 7 159 3)
				)
			)
			(10
				(if (> [egoStats 2] 0)
					(switch local13
						(3
							(curRoom setScript: sJumpThere)
						)
						(5
							(curRoom setScript: sJumpThere)
						)
						(4 (messager say: 0 159 23))
						(else  (messager say: 7 4 3))
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance acidBlood of Prop
	(properties
		noun 17
		signal $5001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(79
				(if local23
					(messager say: 16 6 26)
				else
					(self setScript: sUpperTimer)
				)
			)
			(86
				(= local12 1)
				(self setScript: sUpperTimer)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance acidBlood2 of Prop
	(properties
		noun 17
		signal $5001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(79
				(if local23
					(messager say: 16 6 26)
				else
					(self setScript: sLowerTimer)
				)
			)
			(86
				(= local12 1)
				(self setScript: sLowerTimer)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pGrapnel of Prop
	(properties
		x 222
		y 70
		view 8
		loop 6
		cel 7
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self setScaler: Scaler 51 38 73 52)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (Btst 340)
				(if local23
					(curRoom setScript: sClimbDown)
				else
					(messager say: 16 6 19)
				)
			else
				(curRoom setScript: sClimbDown)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance bloodProp of Prop
	(properties
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 79)
			(messager say: 17 79 10)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance torchEff of Prop
	(properties
		view 775
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (ego y?))
		(= z (+ (ego z?) 1))
		(super doit: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance altarHead of Feature
	(properties
		noun 2
		nsLeft 248
		nsTop 13
		nsRight 308
		nsBottom 66
		x 278
		y 39
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(69
				(switch local13
					(5
						(if (or (not (Btst 340)) (ego has: 53))
							(curRoom setScript: doRitual)
						else
							(messager say: 17 4 0 0)
						)
					)
					(else  (messager say: 7 4 3))
				)
			)
			(4
				(switch local13
					(5 (messager say: 2 4 0 0))
					(else  (messager say: 5 4 3))
				)
			)
			(79
				(= local19 4)
				(curRoom setScript: sThrowIt)
			)
			(86
				(= local19 2)
				(curRoom setScript: sThrowIt)
			)
			(88
				(= local19 3)
				(curRoom setScript: sThrowIt)
			)
			(93
				(= local19 5)
				(curRoom setScript: sThrowIt)
			)
			(21
				(ego use: 6)
				(= local19 0)
				(curRoom setScript: sThrowIt)
			)
			(37
				(if (== (ego has: 5) 1)
					(messager say: 16 6 25)
				else
					(ego use: 5)
					(= local19 1)
					(curRoom setScript: sThrowIt)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance altarBowl of Feature
	(properties
		noun 3
		nsLeft 246
		nsTop 71
		nsRight 278
		nsBottom 81
		x 262
		y 76
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 3 1 0 0))
			(else 
				(altarHead doVerb: theVerb)
			)
		)
	)
)

(instance altarBase of Feature
	(properties
		noun 4
		nsLeft 197
		nsTop 72
		nsRight 308
		nsBottom 93
		x 252
		y 72
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(switch local13
					(4
						(curRoom setScript: sJumpThere)
					)
					(1 (curRoom setScript: sGoOnUp))
					(5 (messager say: 2 4 0 0))
					(else  (messager say: 7 4 3))
				)
			)
			(10
				(if (> [egoStats 2] 0)
					(switch local13
						(4
							(= local15 0)
							(curRoom setScript: sJumpThere)
						)
						(5 (messager say: 0 159 23))
						(else  (messager say: 7 4 3))
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance island of Feature
	(properties
		noun 5
		nsLeft 151
		nsTop 36
		nsRight 188
		nsBottom 54
		approachX 170
		approachY 42
		x 169
		y 45
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 1 10 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33 (messager say: 6 33 0 0))
			(4
				(if (== local13 2)
					(messager say: 5 4 3)
				else
					(messager say: 5 4 0 0)
				)
			)
			(10
				(if (> [egoStats 2] 0)
					(switch local13
						(0
							(curRoom setScript: sJumpThere)
						)
						(3
							(= local14 1)
							(curRoom setScript: sJumpThere)
						)
						(2 (messager say: 5 159 3))
						(else 
							(messager say: 5 159 0 0)
						)
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance upperShelf of Feature
	(properties
		noun 6
		nsLeft 180
		nsTop 19
		nsRight 249
		nsBottom 37
		x 214
		y 28
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 63 39 86 39 85 48 63 47
						yourself:
					)
					6
					7
					3
					(ScriptID 741 0)
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33 (messager say: 6 33 0 0))
			(4 (messager say: 7 4 3))
			(1
				(switch local13
					(2
						(curRoom setScript: sDoYouWanta)
					)
					(3 (messager say: 0 159 23))
					(else  (messager say: 7 4 3))
				)
			)
			(10
				(if (> [egoStats 2] 0)
					(switch local13
						(2
							(curRoom setScript: sDoYouWanta)
						)
						(3 (messager say: 0 159 23))
						(4
							(= local15 1)
							(curRoom setScript: sJumpThere)
						)
						(else  (messager say: 7 4 3))
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lowerShelf of Feature
	(properties
		noun 7
		nsLeft 191
		nsTop 44
		nsRight 248
		nsBottom 62
		x 219
		y 53
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(if (== local13 5)
					(curRoom setScript: sThrowTheGrapnel)
				else
					(messager say: 7 159 3)
				)
			)
			(4
				(switch local13
					(3
						(curRoom setScript: sJumpThere)
					)
					(5
						(curRoom setScript: sJumpThere)
					)
					(4 (messager say: 0 159 23))
					(else  (messager say: 7 4 3))
				)
			)
			(10
				(if (> [egoStats 2] 0)
					(switch local13
						(3
							(curRoom setScript: sJumpThere)
						)
						(5
							(curRoom setScript: sJumpThere)
						)
						(4 (messager say: 0 159 23))
						(else  (messager say: 7 4 3))
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bridge of Feature
	(properties
		noun 8
		nsTop 74
		nsRight 67
		nsBottom 135
		x 33
		y 104
	)
)

(instance bridgeLedge of Feature
	(properties
		noun 9
		nsTop 29
		nsRight 57
		nsBottom 45
		x 28
		y 37
	)
)

(instance spoutPillar of Feature
	(properties
		noun 10
		nsLeft 89
		nsRight 127
		nsBottom 50
		x 108
	)
)

(instance roomExit of Feature
	(properties
		noun 11
		nsLeft 65
		nsTop 22
		nsRight 89
		nsBottom 45
		x 77
		y 33
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (Btst 455)
				(super doVerb: theVerb)
			else
				(messager say: 11 4 24 0)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rock of Feature
	(properties
		noun 12
		nsLeft 146
		nsTop 130
		nsRight 171
		nsBottom 154
		sightAngle 180
		x 158
		y 142
	)
)

(instance littleLedge of Feature
	(properties
		noun 13
		nsLeft 147
		nsTop 62
		nsRight 164
		nsBottom 72
		x 155
		y 67
	)
)

(instance lowLedge of Feature
	(properties
		noun 14
		nsLeft 216
		nsTop 173
		nsRight 285
		nsBottom 189
		x 250
		y 181
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 7 4 3))
			(10 (messager say: 7 4 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fLowerHold of Feature
	(properties
		noun 1
		nsLeft 90
		nsTop 130
		nsRight 140
		nsBottom 158
		approachX 103
		approachY 166
		x 94
		y 95
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local13 0)
					(curRoom setScript: sPullsUp)
				else
					(messager say: 7 4 3)
				)
			)
			(10
				(switch local13
					(1 (messager say: 0 159 23))
					(else  (messager say: 7 4 3))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fUpperPath of Feature
	(properties
		noun 1
		nsLeft 126
		nsTop 48
		nsRight 151
		nsBottom 56
		x 126
		y 48
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 135 141 147 141 147 152 135 152
						yourself:
					)
					7
					5
					1
					sBurnedUp
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(10
				(if (> [egoStats 2] 0)
					(switch local13
						(2
							(curRoom setScript: sJumpThere)
						)
						(0 (messager say: 0 159 23))
						(1 (messager say: 0 159 23))
						(else  (messager say: 7 4 3))
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fLowerPath of Feature
	(properties
		noun 1
		nsLeft 82
		nsTop 164
		nsRight 122
		nsBottom 185
		x 82
		y 164
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 208 117 221 117 222 126 209 126
						yourself:
					)
					1
					7
					5
					sBurnedUp
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(switch local13
					(1
						(curRoom setScript: sPullsUp)
					)
					(0 (messager say: 0 159 23))
					(else  (messager say: 7 4 3))
				)
			)
			(10
				(if (> [egoStats 2] 0)
					(cond 
						(
						(and (== local13 1) (== (ego trySkill: 2 200) 1)) (curRoom setScript: sJumpThere))
						((== local13 0) (messager say: 0 159 23))
						(else (messager say: 7 4 3))
					)
				else
					(messager say: 5 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fSteps of Feature
	(properties
		noun 1
		x 246
		y 92
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 307 95 300 122 246 147 232 138 256 117 254 105 280 92
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event message?) JOY_RIGHT)
				(self onMe: event)
			)
			(event claimed: 1)
			(switch local13
				(1 (curRoom setScript: sGoOnUp))
				(5 (curRoom setScript: sGoDown))
				(else 
					(event claimed: 0)
					(super handleEvent: event &rest)
				)
			)
		else
			(event claimed: 0)
			(super handleEvent: event &rest)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(switch local13
					(1 (curRoom setScript: sGoOnUp))
					(5 (curRoom setScript: sGoDown))
					(else 
						(curRoom doVerb: theVerb)
					)
				)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance roomPoly of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				70
				47
				45
				51
				45
				55
				104
				63
				104
				67
				49
				91
				33
				112
				11
				140
				23
				161
				64
				170
				106
				169
				109
				167
				108
				166
				62
				166
				30
				152
				35
				147
				23
				137
				75
				89
				126
				70
				131
				65
				126
				55
				145
				54
				146
				50
				103
				54
				77
				48
				77
				0
				319
				0
				319
				189
				0
				189
				0
				0
				70
				0
		)
	)
)

(instance lowerPondPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init:
				94
				138
				108
				147
				128
				147
				143
				143
				173
				157
				193
				156
				213
				158
				208
				153
				171
				153
				143
				141
				127
				145
				108
				145
				98
				138
				98
				134
				132
				133
				147
				129
				153
				124
				241
				127
				256
				123
				252
				118
				222
				116
				202
				121
				152
				121
				140
				129
				131
				128
				94
				132
		)
	)
)

(instance islandPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 159 42 170 43 183 43 180 40)
	)
)

(instance highLedgePoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 188 25 201 27 237 27 236 22)
	)
)

(instance lowerLedgePoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 202 48 208 50 248 50 245 46)
	)
)

(instance altarPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init:
				245
				74
				225
				74
				215
				76
				199
				79
				199
				82
				208
				87
				257
				89
				267
				86
				276
				87
				292
				84
				300
				81
				292
				80
				287
				83
				280
				85
				274
				83
				265
				84
				250
				87
				209
				85
				201
				81
				226
				76
				244
				76
		)
	)
)

(instance sndDarkRumble of Sound
	(properties
		number 202
	)
)

(instance sndWater of Sound
	(properties
		number 982
		loop -1
	)
)
