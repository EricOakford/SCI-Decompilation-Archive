;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include sci.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use RangeOsc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use DPath)
(use Reverse)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm64 0
)

(local
	local0
	[local1 2]
	shieldBumps
	toX
	toY
)
(instance rm64 of SQRoom
	(properties
		lookStr {This is where the Sariens placed the Star Generator after lifting it from the Arcada.}
		picture 64
		east 58
		west 60
	)
	
	(method (init)
		(self setRegions: 703)
		(if (ego has: 13)
			(LoadMany 132 537 518 519 516 517 318 522 510)
		)
		(LoadMany 128 164)
		(Load rsSCRIPT 991 819)
		(switch prevRoomNum
			(west
				(= style 3)
				(if (== currentFloor 1)
					(ego setPri: 2 posn: 5 54)
				else
					(ego posn: 5 175)
				)
			)
			(65
				(= currentFloor 2)
				(= style 7)
				(self setScript: fromPad)
			)
			(else 
				(= currentFloor 1)
				(= style 2)
				(ego setPri: 2 posn: 315 54)
			)
		)
		(if (== currentFloor 1)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init: 0 58 319 58 319 189 0 189
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 0 319 0 319 49 34 49 0 49
						yourself:
					)
			)
			(if (!= (DeltaurRegion numGrenades?) 2)
				(LoadMany 128 415 417)
				(if (== (DeltaurRegion egoStatus?) 1)
					(LoadMany 128 67)
				else
					(LoadMany 128 50)
				)
			)
			(LoadMany 128 42 479 67 50)
		else
			(if (Btst 47)
				(LoadMany 128 68 80)
				(self
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								0
								167
								0
								0
								319
								0
								319
								189
								0
								189
								0
								182
								169
								187
								168
								184
								151
								184
								145
								173
								201
								173
								212
								186
								294
								186
								292
								172
								272
								156
								215
								141
								105
								141
								20
								167
							yourself:
						)
				)
			else
				(LoadMany 128 471 91)
				(self
					addObstacle:
						((Polygon new:)
							type: 2
							init: 135 175 186 175 195 187 127 187
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								0
								0
								319
								0
								319
								189
								301
								189
								262
								156
								246
								152
								204
								141
								158
								141
								117
								141
								56
								155
								16
								168
								0
								168
							yourself:
						)
				)
			)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							153
							145
							153
							171
							117
							170
							106
							159
							121
							143
							198
							143
							215
							158
							205
							169
							169
							171
							169
							145
						yourself:
					)
			)
		)
		(guard init:)
		(remote init:)
		(if (and (Btst 47) (== currentFloor 2))
			(grenade init: hide: setScript: sputnik stopUpd:)
		)
		(if (and (Btst 53) (!= prevRoomNum 65))
			(generatorOn init:)
			(if (> (theGame detailLevel:) 1)
				(generatorOn setCycle: Fwd)
			)
		)
		(features
			add: upperLanding emitter tubes space starGenerator
			eachElementDo: #init
		)
		(ego init:)
		(super init:)
		(if (not (Btst 50))
			(leftShieldEmitter setCycle: Fwd init:)
			(rightShieldEmitter setCycle: Fwd setScript: pulse init:)
			(shield setCycle: Fwd init:)
			(remote setCycle: Fwd)
		else
			(remote stopUpd:)
		)
		(if (!= prevRoomNum 65)
			(theMusic
				number: (if (Btst 50) 516 else 517)
				loop: 1
				flags: 1
				play:
				hold: 1
			)
		)
		(if
			(and
				(!= (DeltaurRegion egoStatus?) 1)
				(== currentFloor 2)
				(not (Btst 47))
			)
			(LoadMany 128 471)
			(self setScript: killEgo)
		)
	)
	
	(method (doit)
		(if (not (Btst 53))
			(cond 
				((> (theGame detailLevel:) 1)
					(if
						(and
							(not (rightShieldEmitter script?))
							(not (grenade script?))
							(not (Btst 50))
						)
						(rightShieldEmitter setScript: pulse)
					)
				)
				(
				(and (rightShieldEmitter script?) (not (Btst 50)))
					(emitterSound stop:)
					(leftShieldEmitter show:)
					(rightShieldEmitter show:)
					(shield show:)
					(remote show:)
					(rightShieldEmitter setScript: 0)
				)
			)
		)
		(cond 
			(script 0)
			(
			(and (== currentFloor 1) (!= (ego priority?) 2)) (ego setPri: 2))
			((& (ego onControl: 0) $0002)
				(if (Btst 50)
					(if (Btst 53)
						(self setScript: setToBlow)
					else
						(self setScript: toPad)
					)
				else
					(switch (++ shieldBumps)
						(1
							(self setScript: bumpOffShield)
						)
						(2 (self setScript: toastEgo))
					)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(ego setCycle: 0 setPri: -1)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (!= n 65) (theMusic hold: 0))
		(ego setCycle: 0 setPri: -1)
		(super newRoom: n)
	)
)

(instance leftShieldEmitter of Prop
	(properties
		x 19
		y 146
		description {leftShieldEmitter}
		lookStr {The left hand force field emitter, much like the right, radiates an unknown form of energy that keeps you from approaching the Star Generator.}
		view 164
		loop 3
		priority 12
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (dispose)
		(emitterSound stop:)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
	
	(method (hide)
		(super hide:)
		(emitterSound hold: 0)
	)
	
	(method (show)
		(super show:)
		(emitterSound loop: -1 play: hold: 1)
	)
)

(instance rightShieldEmitter of Prop
	(properties
		x 301
		y 145
		description {rightShieldEmitter}
		lookStr {The right hand force field emitter, in a fashion altogether similar to the left hand one, beams an inexplicable power into the vicinity of the Star Generator that makes it impossible to get near.}
		view 164
		loop 4
		priority 12
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance shield of Prop
	(properties
		x 161
		y 131
		description {shield}
		lookStr {Cowabunga, Wilco - this is IT! It's the unimaginably powerful Star Generator, which must not, at any cost, be allowed to remain in the hands of the evil Sariens! (but you knew that, right?)}
		view 164
		priority 11
		signal $4010
		cycleSpeed 6
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== currentFloor 2))
			(if (== theItem 14)
				(if (not (Btst 50))
					(curRoom setScript: useRemote)
				else
					(Print 64 1)
				)
			)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance generatorOn of Prop
	(properties
		x 161
		y 128
		description {generatorOn}
		lookStr {The pulsing energy surging from the Star Generator tells you that it has been activated and you had better quit hanging around staring at the pretty colors.}
		view 164
		loop 1
		priority 12
		signal $4010
		cycleSpeed 6
		detailLevel 1
	)
)

(instance remote of Prop
	(properties
		x 161
		y 138
		description {remote}
		approachX 161
		approachY 175
		lookStr {A receptor for somekind of remote control signal.}
		view 164
		loop 2
		priority 12
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 3)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(switch theVerb
				(3
					(cond 
						((not (Btst 50)) (Print 64 2))
						((Btst 53) (Print 64 3))
						(else (curRoom setScript: toPad))
					)
				)
				(4
					(if (== theItem 14)
						(if (not (Btst 50))
							(curRoom setScript: useRemote)
						else
							(Print 64 1)
						)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance starGenerator of RegionFeature
	(properties
		description {starGenerator}
		onMeCheck $000a
		lookStr {Cowabunga, Wilco - this is IT! It's the unimaginably powerful Star Generator, which must not, at any cost, be allowed to remain in the hands of the evil Sariens! (but you knew that, right?)}
		level 3
	)
	
	(method (doVerb theVerb theItem)
		(cond 
			((== currentFloor 2)
				(switch theVerb
					(4
						(if (== theItem 14)
							(if (not (Btst 50))
								(curRoom setScript: useRemote)
							else
								(Print 64 1)
							)
						else
							(super doVerb: theVerb theItem &rest)
						)
					)
					(2
						(self
							x: ((user curEvent?) x?)
							y: ((user curEvent?) y?)
						)
						(super doVerb: theVerb theItem &rest)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			((== theVerb 2) (super doVerb: theVerb theItem &rest))
			(else (Print 64 4))
		)
	)
)

(instance grenade of Actor
	(properties
		lookStr {It's the used gas grenade.}
		view 164
		loop 5
		signal $4010
		cycleSpeed 6
		moveSpeed 2
	)
)

(instance guard2 of Actor
	(properties
		y 55
		view 417
		cycleSpeed 6
		moveSpeed 6
	)
)

(instance guard of Actor
	(properties
		x 161
		y 183
		approachX 130
		approachY 188
		yStep 3
		view 164
		loop 7
		signal $4000
		cycleSpeed 6
		xStep 5
		moveSpeed 6
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 5 12 11)
		(if (Btst 47)
			(self cel: 1 stopUpd:)
		else
			(self cel: 0 stopUpd:)
		)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(switch theVerb
				(4
					(switch theItem
						(13 (Print 64 5))
						(0 (Print 64 6))
						(19
							(if (Btst 47)
								(Print 64 7)
							else
								(curRoom setScript: showCard)
							)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(2
					(cond 
						((not (Btst 47))
							(Print 64 8)
							(if
								(and
									(<= 151 (ego x?))
									(<= (ego x?) 171)
									(<= 163 (ego y?))
									(<= (ego y?) 183)
								)
								(Print 64 9)
							)
						)
						((and (Btst 47) (not (ego has: 14))) (Print 64 10))
						(else (Print 64 11))
					)
				)
				(5
					(if (not (Btst 47))
						(if (ego has: 0) (Print 64 12) else (Print 64 13))
					else
						(Print 64 14)
					)
				)
				(3
					(cond 
						((not (Btst 47)) (Print 64 15))
						((and (Btst 47) (not (ego has: 14))) (curRoom setScript: getRemote))
						(else (Print 64 16))
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		else
			(switch theVerb
				(2
					(cond 
						((not (Btst 47)) (Print 64 17))
						((and (Btst 47) (not (ego has: 14))) (Print 64 18))
						(else (Print 64 19))
					)
				)
				(4
					(if (== theItem 13)
						(if (not (Btst 47))
							(curRoom setScript: dropTheGrenade)
						else
							(Print 64 5)
						)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance toPad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 12 setMotion: DPath 158 149 147 145 self)
			)
			(1 (Face ego remote self))
			(2
				(ego view: 68 setLoop: 5 cel: 0 setCycle: End self)
			)
			(3 (curRoom newRoom: 65))
		)
	)
)

(instance fromPad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normal: 0
					posn: 147 145
					view: 68
					setPri: 12
					setLoop: 5
					cel: 6
					setCycle: Beg self
				)
			)
			(1
				(if (Btst 53) (theMusic hold: 0))
				(EgoStatusCheck)
				(ego
					loop: 2
					setPri: 12
					setMotion: DPath 158 149 153 176 self
				)
			)
			(2
				(ego setPri: -1)
				(if (Btst 53)
					(Face ego generatorOn)
					(ego moveHead: 0)
					(generatorOn init: hide: setCycle: Fwd cycleSpeed: 51)
					(= register 4)
					(= ticks 18)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(3
				(theMusic number: 516 loop: 0 play: hold: 1)
				(generatorOn cycleSpeed: register hide:)
				(= ticks (* register 6))
			)
			(4
				(generatorOn show:)
				(= ticks 18)
			)
			(5
				(if (-- register) (= state (- state 3)))
				(= ticks 18)
			)
			(6
				(generatorOn cycleSpeed: 6)
				(ego moveHead: 1)
				(DeltaurRegion setScript: (ScriptID 703 23))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance killEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(leftShieldEmitter dispose:)
				(rightShieldEmitter dispose:)
				(ego setMotion: MoveTo 33 177 self)
			)
			(1
				(Print 64 20)
				(ego view: 41 loop: 0 cel: 0 setCycle: CT 2 1 self)
			)
			(2
				(ego cycleSpeed: 5 setCycle: RangeOsc -1 2 3)
				(= ticks 18)
			)
			(3
				(guard
					view: 471
					loop: 0
					cel: 0
					cycleSpeed: 6
					moveSpeed: 6
					setCycle: End self
				)
			)
			(4
				(guard
					setLoop: 1
					cel: (guard lastCel:)
					posn: 137 183
					setCycle: Walk
					setMotion: MoveTo 76 177 self
				)
			)
			(5
				(ego
					view: 471
					setLoop: 4
					cel: 1
					posn: 104 124
					setCycle: Rev
					setPri: 15
					setCycle: 0
					hide:
				)
				(guard
					view: 471
					loop: 3
					cycleSpeed: 6
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(6
				(guard setCycle: RangeOsc 5 4 5 self)
			)
			(7
				(guard setCycle: RangeOsc 3 6 8 self)
			)
			(8
				(guard setCycle: CT 13 1 self)
			)
			(9
				(guard setCycle: End)
				(ego
					show:
					setStep: 8 8
					cycleSpeed: 6
					setCycle: Fwd
					setMotion: JumpTo 188 51 self
				)
			)
			(10
				(ego setMotion: JumpTo 319 190)
				(guard
					view: 471
					loop: 2
					cycleSpeed: 2
					cel: 0
					setCycle: End self
				)
			)
			(11
				(ego
					view: 471
					loop: 5
					cel: 0
					cycleSpeed: 6
					setMotion: 0
					setCycle: End
				)
				(= seconds 4)
			)
			(12
				(EgoDead 940 2 0 64 21)
				(self dispose:)
			)
		)
	)
)

(instance pulse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leftShieldEmitter show:)
				(rightShieldEmitter show:)
				(= seconds (Random 1 5))
			)
			(1
				(leftShieldEmitter hide:)
				(rightShieldEmitter hide:)
				(= seconds (Random 1 5))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance bounceGrenade of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 537 loop: 1 play:)
				(grenade setCycle: Fwd setMotion: JumpTo 157 125 self)
			)
			(1
				(guard view: 42 loop: 8 cel: 0 setCycle: CT 1 1)
				(grenade setCycle: Rev setMotion: JumpTo 160 96 self)
			)
			(2
				(grenade
					setMotion: JumpTo (ego x?) (- (guard y?) 5) self
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance dropTheGrenade of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego put: 13)
				(= local0 ((user curEvent?) x?))
				(if
					(and
						(< (guard nsLeft?) local0)
						(< local0 (guard nsRight?))
					)
					(ego setMotion: PolyPath (guard x?) 57 self)
				else
					(ego setMotion: PolyPath ((user curEvent?) x?) 57 self)
				)
			)
			(1
				(if 0 (ego setLoop: 0) else (ego setLoop: 1))
				(if (== (DeltaurRegion egoStatus?) 2)
					(ego setLoop: (+ (ego loop?) 4))
				)
				(ego view: 42 cel: 0 setCycle: CT 6 1 self)
			)
			(2
				(grenade
					yStep: 4
					startUpd:
					view: 164
					setPri: 15
					setLoop: 5
					cel: 0
					posn: (- (ego x?) 3) (- (ego y?) 20)
					init:
				)
				(ego setCycle: End self)
			)
			(3
				(if
					(and
						(< (guard nsLeft?) local0)
						(< local0 (guard nsRight?))
						(not (Btst 47))
					)
					(self setScript: bounceGrenade self)
				else
					(grenade
						setCycle: Fwd
						setMotion: JumpTo (ego x?) (- (guard y?) 5) self
					)
				)
			)
			(4
				(soundFx number: 518 loop: 0 play:)
				(theMusic2 number: 519 loop: 0 play:)
				(if
					(and
						(< (guard nsLeft?) local0)
						(< local0 (guard nsRight?))
						(not (Btst 47))
					)
					(grenade dispose:)
					(Bset 47)
					(SolvePuzzle 5 168)
					(guard setScript: guardDies self)
				else
					(grenade view: 479 loop: 0 cel: 0 setCycle: End self)
				)
			)
			(5
				(if
					(not
						(if (< (guard nsLeft?) local0)
							(< local0 (guard nsRight?))
						)
					)
					(grenade dispose:)
					(if (not (Btst 47))
						(Print 64 22)
						(Print 64 23)
					else
						(Print 64 24)
					)
				)
				(ego
					setLoop: (+ (ego loop?) 2)
					cel: 0
					setCycle: End self
				)
			)
			(6
				(EgoStatusCheck)
				(ego loop: 2 setPri: 2)
				(if
					(and
						(not (DeltaurRegion numGrenades?))
						(not (Btst 47))
					)
					(self setScript: shootTheEgo self)
				else
					(self cue:)
				)
			)
			(7 (HandsOn) (self dispose:))
		)
	)
)

(instance guardDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 0)
				(guard
					view: 42
					loop: 8
					cycleSpeed: 6
					setCycle: CT 8 1 self
				)
			)
			(1
				(guard cycleSpeed: 12 setCycle: RangeOsc 2 7 8 self)
			)
			(2
				(guard setCycle: RangeOsc 2 8 9 self)
			)
			(3
				(guard setCycle: CT 10 1 self)
			)
			(4
				(soundFx number: 522 loop: 1 play:)
				(guard setCycle: RangeOsc 2 11 12 self)
			)
			(5
				(guard view: 164 setLoop: 7 setCel: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getRemote of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register (< (ego x?) (guard x?)))
				(guard ignoreActors: 1 illegalBits: 0)
				(if register
					(ego
						ignoreControl: -32768
						setMotion: PolyPath 161 185 self
					)
				else
					(ego
						ignoreControl: -32768
						setMotion: PolyPath 213 181 self
					)
				)
			)
			(1 (Face ego remote self))
			(2
				(if register
					(ego setLoop: 5)
				else
					(ego setPri: (- (ego priority?) 1) setLoop: 4)
				)
				(ego view: 68 cel: 0 cycleSpeed: 12 setCycle: End self)
			)
			(3
				(guard setCel: 2)
				(ego setCycle: CT (- (ego lastCel:) 1) -1 self)
			)
			(4
				(guard setCel: 1)
				(ego setCycle: CT (ego lastCel:) 1 self)
			)
			(5
				(Print 64 25)
				(ego setCycle: Beg self)
			)
			(6
				(EgoStatusCheck)
				(ego loop: 6)
				(= ticks 18)
			)
			(7
				(if register
					(ego setMotion: PolyPath 125 184 self)
				else
					(ego setMotion: PolyPath 226 178 self)
				)
			)
			(8
				(ego get: 14)
				(SolvePuzzle 3 170)
				(Face ego remote)
				(ego observeControl: -32768)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance turnForceFieldOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rightShieldEmitter setScript: 0 dispose:)
				(leftShieldEmitter dispose:)
				(Print 64 26)
				(shield setLoop: 6 cel: 0 setCycle: End self)
			)
			(1
				(shield cycleSpeed: 2 setCycle: RangeOsc 4 0 0 self)
			)
			(2
				(theMusic hold: 0 fade:)
				(shield dispose:)
				(SolvePuzzle 3 171)
				(self dispose:)
			)
		)
	)
)

(instance useRemote of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego 160 157 self)
			)
			(1
				(Bset 50)
				(cond 
					((and (< (ego x?) 160) (< (ego y?) 157)) (ego loop: 0))
					((and (> (ego x?) 160) (< (ego y?) 157)) (ego loop: 1))
					((and (< (ego x?) 160) (> (ego y?) 157)) (ego loop: 2))
					(else (ego loop: 3))
				)
				(ego view: 68 cel: 0 cycleSpeed: 6 setCycle: End self)
			)
			(2
				(soundFx number: 318 loop: 0 play:)
				(ego setCycle: Beg self)
			)
			(3
				(= register (+ (ego loop?) 4))
				(EgoStatusCheck)
				(ego loop: register)
				(self setScript: turnForceFieldOff self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance toastEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (OneOf (ego loop?) 0 4 6)
					(= register 1)
				else
					(= register 0)
				)
				(if (== (DeltaurRegion egoStatus?) 1)
					(ego setLoop: 2)
				else
					(ego setLoop: 0)
				)
				(= toY (+ (ego y?) 10))
				(if register
					(= toX (- (ego x?) 30))
				else
					(= toX (+ (ego x?) 30))
				)
				(ego
					loop: (+ (ego loop?) register)
					view: 80
					cel: 0
					cycleSpeed: 3
					setCycle: CT 13 1 self
					setMotion: JumpTo toX toY
				)
			)
			(1
				(ego cycleSpeed: 10 setCycle: RangeOsc 5 11 13 self)
			)
			(2
				(EgoDead 945 0 0 64 27)
				(self dispose:)
			)
		)
	)
)

(instance showCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego x?) 160)
					(ego setMotion: PolyPath 130 188 self)
				else
					(ego setMotion: PolyPath 196 188 self)
				)
			)
			(1 (Face ego guard self))
			(2
				(ego
					view: 91
					loop: (if (< (ego x?) 160) 0 else 1)
					cel: 0
					cycleSpeed: 5
					setCycle: End self
				)
			)
			(3
				(Printf 64 28 selfDestructCode)
				(ego setCycle: Beg self)
			)
			(4
				(EgoStatusCheck)
				(ego
					loop: (if (ego loop?) 7 else 6)
					setHeading: (if (ego loop?) 315 else 45)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bumpOffShield of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 64 29)
				(ego setMotion: PolyPath 163 172 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sputnik of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(if (not (Btst 50))
					(leftShieldEmitter hide:)
					(rightShieldEmitter hide: setScript: 0)
				)
				(grenade
					lookStr: {Da Cnythuk}
					startUpd:
					show:
					setLoop: 8
					posn: 44 0
					setPri: 1
					cycleSpeed: 5
					moveSpeed: 3
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo 301 110 self
				)
			)
			(2
				(if (not (Btst 50))
					(rightShieldEmitter hide: setScript: pulse)
				)
				(grenade dispose:)
				(self dispose:)
			)
		)
	)
)

(instance setToBlow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 64 30)
				(ego setMotion: PolyPath 163 172 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance shootTheEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego 0 55 self)
			)
			(1
				(Print 64 31)
				(if (> (ego x?) 120)
					(= cycles 10)
				else
					(ego
						setCycle: Rev
						setLoop: 1
						setMotion: MoveTo 156 53 self
					)
				)
			)
			(2
				(ego setCycle: 0)
				(guard2 init: setCycle: Walk setMotion: MoveTo 36 55 self)
			)
			(3
				(guard2 view: 415 cel: 0 setCycle: End self)
			)
			(4
				(if (== (DeltaurRegion egoStatus?) 1)
					(ego view: 67)
				else
					(ego view: 50)
				)
				(ego setLoop: 1 cycleSpeed: 6 cel: 0 setCycle: End self)
			)
			(5
				(EgoDead 940 2 0 64 32)
				(self dispose:)
			)
		)
	)
)

(instance upperLanding of RegionFeature
	(properties
		description {upper landing}
		onMeCheck $0020
		lookStr {It's the walkway above the Star Generator.}
		level 3
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance emitter of RegionFeature
	(properties
		description {force field emitter_}
		onMeCheck $4000
		lookStr {This is one of the force field's emitters.}
		level 3
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance tubes of RegionFeature
	(properties
		description {tubes}
		onMeCheck $0010
		lookStr {The Wally Tubes look strangely empty without Thunder Agents in them.}
		level 3
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance space of RegionFeature
	(properties
		description {outer space_}
		onMeCheck $0200
		lookStr {Looking into the vast reaches of space gives you the curious illusion of being tiny, unimportant, insignificant, negligible, and totally irrelevant in the scheme of things. Then you realize it's not an illusion.}
		level 3
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 4)
			(switch theItem
				(12
					(if
						(or
							(and (== currentFloor 1) (> ((user curEvent?) y?) 60))
							(and (== currentFloor 2) (< ((user curEvent?) y?) 61))
						)
						(Print 64 0)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(13
					(if (< (ego y?) 70)
						(curRoom setScript: dropTheGrenade)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		)
	)
)

(instance emitterSound of Sound
	(properties
		number 510
	)
)
