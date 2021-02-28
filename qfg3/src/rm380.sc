;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use Target)
(use EgoDead)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm380 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm380 of Rm
	(properties
		noun 5
		picture 380
	)
	
	(method (init)
		(HandsOff)
		(= global426 0)
		(LoadMany 130 956 991)
		(ego
			x: 164
			y: 68
			setScale: Scaler 75 50 95 0
			init:
			normalize: 2
		)
		(super init:)
		(cSound number: 380 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						92
						85
						92
						81
						103
						60
						109
						70
						115
						70
						136
						140
						179
						177
						184
						251
						184
						293
						152
						232
						110
						141
						96
						133
						87
						178
						80
						178
						76
						227
						76
						263
						69
						287
						71
						319
						53
						319
						1
						0
						1
					yourself:
				)
		)
		(if (Btst 52) (= local3 1))
		(if (Btst 55) (= local3 2))
		(if (Btst 148) (= local3 3))
		(holes init:)
		(sky init:)
		(switch local3
			(0
				(bat1 setPri: 14 init: stopUpd:)
				(bat2 setPri: 14 init: stopUpd:)
				(bat3 setPri: 14 init: stopUpd:)
				(smallBat setPri: 14 init: stopUpd:)
				(mainVine init:)
				(vine1 init: stopUpd:)
				(vine2 init: stopUpd:)
				(vine3 init: stopUpd:)
				(vine4 init: stopUpd:)
				(vine5 init: stopUpd:)
			)
			(1
				(mainVine init:)
				(ego code: ambushChek)
			)
			(2
				(if (not (Btst 148))
					(stoneRing setPri: 14 init: approachVerbs: 4 stopUpd:)
				)
				(smallBat setPri: 14 init: stopUpd: setScript: thankYou)
			)
		)
		(HandsOff)
		(self setScript: egoEnters)
	)
	
	(method (doit)
		(if
			(and
				(not (== curRoom script))
				(or
					(and
						(not (== (curRoom script?) egoEnters))
						(& (ego onControl: 1) $0004)
					)
					(< (ego y?) 65)
				)
			)
			(curRoom setScript: getOut)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset 54)
		(if gNewList (gNewList eachElementDo: #dispose))
		(LoadMany 0 956 991 37)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(81
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(83
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(88
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(20
					(if (not (curRoom script?))
						(++ global426)
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(33
					(if (not (curRoom script?))
						(curRoom setScript: blastVine 0 theVerb)
					)
				)
				(1
					(if (not local0)
						(messager say: 5 1)
					else
						(messager say: 5 1 15)
					)
					(return 1)
				)
				(75 (messager say: 1 6 18))
				(4
					(if
						(and
							(> ((User curEvent?) y?) 30)
							(== (grabEgo state?) -1)
						)
						(curRoom setScript: getRocks)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance getOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego get: 10 global426)
				(= seconds 1)
			)
			(1
				(= global426 0)
				(globalSound setLoop: 1)
				(curRoom newRoom: 150)
			)
		)
	)
)

(instance thankYou of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1 (client setCycle: End self))
			(2 (client dispose:))
		)
	)
)

(instance savedBatEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 120 110 self)
			)
			(1
				(messager say: 1 6 17 0 self)
			)
			(2
				(smallBat setCycle: End self)
			)
			(3
				(smallBat dispose:)
				(self dispose:)
			)
		)
	)
)

(instance blastVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(HandsOff)
				(self setScript: (ScriptID 32 0) self register)
			)
			(1
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance throwHook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: 35) (self dispose:))
				(HandsOff)
				(ego setMotion: PolyPath 90 72 self)
			)
			(1
				(ego setMotion: PolyPath 93 89 self)
			)
			(2
				(ego view: 8 setLoop: 4 setCycle: End self)
				(globalSound number: 721 setLoop: 1 play: 127)
			)
			(3
				(grapThingy
					x: 95
					y: 79
					setLoop: 6
					setScale:
					init:
					setMotion: JumpTo 165 143 self
				)
			)
			(4
				(mainVine cel: 1)
				(mainVine setScript: vinesTwist)
				(ego setCycle: Beg)
				(grapThingy setMotion: JumpTo 95 79 self)
				(globalSound number: 511 setLoop: 1 play: 127)
			)
			(5
				(grapThingy dispose:)
				(messager say: 1 6 11 0 self)
			)
			(6
				(ego get: 35 solvePuzzle: 256 8 normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoHacksBat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 55)
				(flutterBackToRock dispose:)
				(ego setMotion: PolyPath 104 178 self)
			)
			(1
				(ego view: 385 loop: 1 cel: 0 setCycle: End self)
				(globalSound number: 520 setLoop: 1 play: 127)
			)
			(2 (ego setCycle: Beg self))
			(3 (messager say: 1 6 9 0 self))
			(4
				(if
					(and
						(== heroType 0)
						(not (ego has: 16))
						(ego code: ambushChek)
						(mainVine init: setCycle: Beg)
					)
				)
				(ego normalize:)
				(smallBat dispose:)
				(Bset 52)
				(Bset 19)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance flutterBackToRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smallBat loop: 6 cel: 8 setCycle: Beg self)
			)
			(1
				(globalSound number: 383 setLoop: 1 play: 127)
				(smallBat loop: 5 setMotion: JumpTo 79 167 self)
				(ego code: egoChek)
			)
			(2
				(smallBat loop: 1 cel: 0)
				(= seconds 30)
			)
			(3
				(globalSound stop:)
				(smallBat loop: 0 setCycle: End self)
			)
			(4
				(smallBat hide:)
				(self dispose:)
			)
		)
	)
)

(instance smallBatJump of Script
	(properties)
	
	(method (doit)
		(if (== local0 1)
			(Bset 55)
			(if (not (Btst 257)) (ego addHonor: 20))
			(ego solvePuzzle: 257 8 9)
			(smallBat setCycle: 0)
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 7))
			(1
				(ego code: 0)
				(smallBat loop: 2 setCycle: End self)
			)
			(2
				(globalSound number: 383 setLoop: 1 play: 127)
				(smallBat
					setLoop: 4
					setCycle: Fwd
					setMotion: JumpTo 163 147 self
				)
			)
			(3
				(smallBat setMotion: MoveTo 192 126 self)
			)
			(4
				(mainVine setCycle: Fwd)
				(vine4 setPri: (+ (smallBat priority?) 1) setCycle: Fwd)
				(smallBat cycleSpeed: 6 loop: 4 setCycle: End self)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(5
				(HandsOn)
				(globalSound number: 382 setLoop: -1 play: 127)
				(if (> (theGame detailLevel:) 2)
					(smallBat setCycle: Fwd)
				else
					(smallBat stopUpd:)
				)
				(= seconds 20)
			)
			(6
				(smallBat setCycle: End self)
			)
			(7
				(vine4 loop: 5 setCycle: End self)
			)
			(8
				(mainVine setCycle: 0)
				(smallBat hide:)
				(globalSound stop:)
				(Bset 52)
				(if (not local2) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance ambush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany 128 6)
				(HandsOff)
				(vine1 loop: 2 cel: 5 init: setCycle: Beg)
				(vine2 loop: 3 cel: 5 init: setCycle: Beg)
				(vine3 loop: 2 cel: 5 init: setCycle: Beg)
				(vine4 loop: 3 cel: 4 init: setCycle: Beg)
				(vine5 loop: 2 cel: 5 init: setCycle: Beg)
				(mainVine setLoop: 0 setCycle: Fwd)
				(globalSound number: 918 setLoop: 1 play: 127)
				(ego view: 11 setCycle: End self)
			)
			(1
				(vine1 loop: 0 setCycle: Fwd)
				(vine2 loop: 0 setCycle: Fwd)
				(vine3 loop: 0 setCycle: Fwd)
				(vine4 loop: 0 setCycle: Fwd)
				(vine5 loop: 0 setCycle: Fwd)
				(ego cycleSpeed: 8 setCycle: ForwardCounter 3 self)
				(globalSound number: 918 setLoop: -1 play: 127)
			)
			(2
				(ego view: 6 setCycle: End self)
			)
			(3
				(EgoDead 6 0 386 End)
				(self dispose:)
			)
		)
	)
)

(instance chopVines of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local0 1)
				(grabEgo dispose:)
				(ego view: 385 loop: 0 cel: 0 setCycle: End self)
				(globalSound number: 520 setLoop: 1 play: 127)
			)
			(1
				(mainVine setScript: vinesGoDown)
				(ego setCycle: Beg self)
			)
			(2
				(if (not (cast contains: smallBat))
					(ego code: egoChek)
				)
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance vinesGoDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(vine1 loop: 2 cel: 0 setCycle: End ignoreActors: 1)
				(vine2 loop: 3 cel: 0 setCycle: End ignoreActors: 1)
				(vine3 loop: 2 cel: 0 setCycle: End ignoreActors: 1)
				(vine4 loop: 3 cel: 0 setCycle: End ignoreActors: 1)
				(vine5 loop: 2 cel: 0 setCycle: End ignoreActors: 1)
				(mainVine
					loop: 1
					cel: 0
					setCycle: End self
					ignoreActors: 1
				)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(1 (= seconds 1))
			(2
				(vine1 stopUpd:)
				(vine2 stopUpd:)
				(vine3 stopUpd:)
				(vine4 stopUpd:)
				(vine5 stopUpd:)
				(mainVine stopUpd:)
				(if (Btst 55) (smallBat setScript: flutterBackToRock))
				(self dispose:)
			)
		)
	)
)

(instance grabEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Bset 115)
				(= local2 1)
				(vine3 setCycle: End self)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(1 (messager say: 1 6 3 0 self))
			(2
				(ego
					view: 11
					cycleSpeed: 8
					setCycle: Fwd
					setMotion: MoveTo 115 93
				)
				(HandsOn)
				(theIconBar disable: 1 6 5 3 advanceCurIcon:)
				(vine3 setCycle: Fwd)
				(mainVine setCycle: Fwd)
				(globalSound number: 918 setLoop: 1 play: 127)
				(= seconds 1)
			)
			(3
				(if (<= [egoStats 16] 1) (EgoDead 7 0 386 End))
				(ego takeDamage: 1)
				(= seconds 1)
			)
			(4
				(= state (- state 2))
				(self cue:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 80 self)
			)
			(1 (= cycles 10))
			(2
				(if (not (Btst 54))
					(messager say: 1 6 2 0 self)
				else
					(self cue:)
				)
			)
			(3
				(if (Btst 150)
					(messager say: 1 6 14 0 self)
				else
					(self cue:)
				)
			)
			(4
				(cond 
					((== local3 2) (ego setScript: savedBatEntrance) (HandsOn))
					(
					(and (Btst 52) (== heroType 0) (not (ego has: 16))) (ego code: ambushChek) (HandsOn))
					((and (not (Btst 52)) (not (Btst 55)))
						(bat1 setScript: bat1Hoark)
						(globalSound number: 381 setLoop: 1 play: 127)
						(ego code: egoChek)
					)
					(else (HandsOn))
				)
				(self dispose:)
			)
		)
	)
)

(instance vinesTwist of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mainVine setCycle: End)
				(if (cast contains: vine1)
					(vine1 setCycle: End)
					(vine2 setCycle: End)
					(vine3 setCycle: End)
					(vine4 setCycle: End)
					(vine5 setCycle: End self)
				)
				(globalSound number: 918 setLoop: 1 play: 127)
			)
			(1
				(mainVine setCycle: Beg)
				(if (cast contains: vine1)
					(vine1 setCycle: Beg)
					(vine2 setCycle: Beg)
					(vine3 setCycle: Beg)
					(vine5 setCycle: Beg)
				)
				(globalSound number: 918 setLoop: 1 play: 127)
				(= seconds 2)
			)
			(2
				(vine1 stopUpd:)
				(vine2 stopUpd:)
				(vine3 stopUpd:)
				(vine4 stopUpd:)
				(vine5 stopUpd:)
				(mainVine stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance bat1Hoark of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bat1 moveSpeed: 1 setLoop: 2 setCycle: End self)
			)
			(1
				(globalSound number: 383 setLoop: 1 play: 127)
				(bat1 setLoop: 4 setCycle: ForwardCounter 3 self)
			)
			(2 (bat1 setCycle: End self))
			(3
				(globalSound number: 383 setLoop: 1 play: 127)
				(bat1 setMotion: JumpTo 169 132 self)
			)
			(4 (bat1 setCycle: Beg self))
			(5
				(globalSound number: 383 setLoop: 1 play: 127)
				(mainVine setScript: vinesTwist)
				(bat1 setCycle: Fwd setMotion: MoveTo 214 105 self)
			)
			(6
				(bat1 setMotion: MoveTo 360 110 self)
			)
			(7
				(globalSound number: 383 setLoop: 1 play: 127)
				(bat1 setLoop: 5 setMotion: JumpTo 42 160 self)
			)
			(8
				(bat1 setLoop: 2 setCycle: Beg self)
			)
			(9
				(globalSound number: 381 setLoop: 1 play: 127)
				(bat1 setLoop: 0 setCycle: 0 stopUpd:)
				(HandsOn)
				(if
					(and
						(Btst 54)
						(not (Btst 55))
						(not (Btst 52))
						(not (== local0 1))
					)
					(smallBat setScript: smallBatJump)
				)
				(self dispose:)
			)
		)
	)
)

(instance batsComeOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bat1 show: setCycle: Beg self)
			)
			(1
				(bat2 show: setCycle: Beg self)
			)
			(2
				(if
				(and (cast contains: smallBat) (not (Btst 55)))
					(smallBat show: setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(3
				(bat3 show: setCycle: Beg self)
			)
			(4
				(globalSound number: 381 setLoop: 1 play: 127)
				(= local1 0)
				(= seconds 2)
			)
			(5
				(bat1 stopUpd:)
				(bat2 stopUpd:)
				(bat3 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance theyHide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 381 setLoop: 1 play: 127)
				(bat1 setCycle: End self)
			)
			(1
				(bat1 hide:)
				(if
					(and
						(cast contains: smallBat)
						(not (== (smallBat script?) flutterBackToRock))
						(not (Btst 55))
					)
					(smallBat setCycle: End self)
				else
					(self cue:)
				)
			)
			(2
				(if
					(and
						(cast contains: smallBat)
						(not (== (smallBat script?) flutterBackToRock))
						(not (Btst 55))
					)
					(smallBat hide:)
				)
				(bat3 setCycle: End self)
			)
			(3
				(bat3 hide:)
				(bat2 setCycle: End self)
			)
			(4
				(bat2 hide:)
				(self dispose:)
			)
		)
	)
)

(instance getRocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 4
					loop: (mod (ego loop?) 2)
					setCycle: End self
				)
				(= register (Narrator y?))
			)
			(1
				(Narrator y: 20)
				(messager say: 1 6 19 0 self)
				(ego get: 23 (Random 2 5))
			)
			(2
				(Narrator y: register)
				(ego setCycle: Beg self)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance grapThingy of Actor
	(properties
		view 21
		loop 6
		signal $4000
	)
)

(instance stoneRing of Prop
	(properties
		x 95
		y 185
		noun 3
		approachX 109
		approachY 163
		view 384
		signal $4000
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (== (stoneRing cel?) 0)
						(messager say: 3 1)
					else
						(messager say: 3 1 16)
					)
					(return 1)
				)
				(4
					(if (not (ego has: 35))
						(ego get: 35 solvePuzzle: 256 8)
						(if (== (stoneRing cel?) 0) (stoneRing setCycle: End))
					)
					(if (not (ego has: 34))
						(ego get: 34)
						(if (== (stoneRing cel?) 0) (stoneRing setCycle: End))
					)
					(if (not (Btst 148))
						(messager say: 3 4 10)
					else
						(messager say: 3 4 20)
					)
					(Bset 148)
					(return 1)
				)
				(26
					(if (not (curRoom script?))
						(AutoTarget 95 180)
						(curRoom setScript: (ScriptID 37 0) 0 mainVine)
					)
				)
				(-82
					(messager say: 1 6 21)
					(if (not (ego has: 35))
						(ego solvePuzzle: 256 8 get: 35)
					)
					(if (not (ego has: 34)) (ego get: 34))
					(stoneRing setCel: 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance smallBat of Actor
	(properties
		x 79
		y 167
		noun 7
		view 383
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22
				(if (< (self distanceTo: ego) 30)
					(messager say: 1 6 5)
					(ego drop: 12 1 addHonor: 20)
				else
					(messager say: 1 6 12)
				)
			)
			(11
				(if
					(and
						(not (ego script?))
						(== (smallBat script?) flutterBackToRock)
					)
					(ego setScript: egoHacksBat)
				)
			)
			(20
				(if
					(and
						(not (ego script?))
						(== (smallBat script?) flutterBackToRock)
					)
					(ego setScript: egoHacksBat)
				else
					(super doVerb: theVerb)
				)
			)
			(12
				(if
					(and
						(not (ego script?))
						(== (smallBat script?) flutterBackToRock)
					)
					(ego setScript: egoHacksBat)
				)
			)
			(66
				(if (Btst 55)
					(messager say: 1 6 5)
					(= [egoStats 17] (/ [egoStats 17] 2))
					(ego addHonor: 20)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bat3 of Prop
	(properties
		x 118
		y 184
		noun 6
		view 382
		signal $4000
	)
)

(instance bat2 of Prop
	(properties
		x 58
		y 182
		noun 6
		view 382
		signal $4000
	)
)

(instance bat1 of Actor
	(properties
		x 42
		y 160
		noun 6
		view 382
		signal $4000
	)
)

(instance vine1 of TargProp
	(properties
		x 179
		y 160
		noun 9
		view 380
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(83
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(88
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(20
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(33
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(4
				(if (not (curRoom script?))
					(ego setMotion: PolyPath 127 95)
				)
			)
			(11
				(ego setMotion: PolyPath 127 95)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine2 of TargProp
	(properties
		x 112
		y 140
		noun 9
		view 380
		cel 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(vine1 doVerb: theVerb)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine3 of TargProp
	(properties
		x 115
		y 95
		noun 9
		view 380
		cel 8
		signal $4000
	)
	
	(method (doit)
		(if
			(and
				(not local0)
				(not local2)
				(< (GetDistance x y (ego x?) (ego y?) perspective) 20)
			)
			(self setScript: grabEgo)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20
				(if (== (self script?) grabEgo)
					(ego setScript: chopVines)
				else
					(vine1 doVerb: theVerb)
				)
			)
			(11
				(if (== (self script?) grabEgo)
					(ego setScript: chopVines)
				else
					(ego setMotion: PolyPath 127 95)
				)
			)
			(12
				(if (== (self script?) grabEgo)
					(ego setScript: chopVines)
				)
			)
			(81
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(83
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(88
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(20
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(33
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(4 (vine1 doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine4 of TargProp
	(properties
		x 194
		y 115
		noun 9
		view 380
		cel 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(vine1 doVerb: theVerb)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance vine5 of TargProp
	(properties
		x 251
		y 158
		noun 9
		view 380
		cel 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(vine1 doVerb: theVerb)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance mainVine of TargProp
	(properties
		x 165
		y 143
		noun 8
		view 381
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-82
				(messager say: 1 6 4)
				(ego solvePuzzle: 256 8 get: 35)
				(self setScript: vinesTwist)
			)
			(82
				(cond 
					((ego has: 35) (messager say: 1 6 13))
					((not (curRoom script?))
						(AutoTarget 165 138)
						(curRoom setScript: (ScriptID 37 0) 0 mainVine)
					)
				)
			)
			(16
				(cond 
					((ego has: 35) (messager say: 1 6 13))
					(
						(and
							(not (vine3 script?))
							(not (curRoom script?))
							(not (ego has: 35))
						)
						(curRoom setScript: throwHook)
					)
				)
			)
			(20
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(33
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(81
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(83
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(88
				(if (not (curRoom script?))
					(self setScript: blastVine 0 theVerb)
				)
			)
			(4
				(if (not (curRoom script?))
					(ego setMotion: PolyPath 129 95)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(self setScript: vinesGoDown)
	)
)

(instance holes of Feature
	(properties
		x 91
		y 172
		noun 2
		nsTop 155
		nsLeft 53
		nsBottom 189
		nsRight 130
		sightAngle 180
	)
)

(instance sky of Feature
	(properties
		x 159
		y 21
		noun 4
		nsBottom 42
		nsRight 319
		sightAngle 180
	)
)

(instance egoChek of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(< (ego x?) 200)
					(> (ego y?) 91)
					(== local1 0)
					(not (bat1 script?))
				)
				(= local1 1)
				(curRoom setScript: theyHide)
			)
			(
			(and (== local1 1) (> (ego x?) 127) (< (ego y?) 91)) (= local1 0) (curRoom setScript: batsComeOut))
		)
	)
)

(instance ambushChek of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(not (mainVine script?))
				(< (ego distanceTo: mainVine) 30)
			)
			(mainVine setScript: ambush)
		)
	)
)
