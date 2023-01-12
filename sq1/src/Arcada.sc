;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARCADA) ;700
(include game.sh)
(use Main)
(use Intrface)
(use Elevator)
(use Osc)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	arcadaRegion 0
	ArcadaCheck 1
	detonationBox 2
)

(local
	local0
	local1
	local2
)

(procedure (ArcadaCheck param1 param2)
	(return
		(if (& (arcadaRegion param1?) param2)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (DisplayDetonationBox)
	(if (arcadaRegion theTimeID?)
		(Display 700 3 p_restore (arcadaRegion theTimeID?))
		(arcadaRegion theTimeID: 0)
	)
	(detonationBox
		x:
			(if
				(or
					(and (> 320 (ego x?)) (> (ego x?) 160))
					(< (ego x?) 0)
				)
				30
			else
				264
			)
	)
	(detonationBox
		y: (if (== currentFloor 1) 190 else 44)
		forceUpd:
		stopUpd:
	)
)

(class Sarien of Actor
	(properties
		description {sarien}
		lookStr {These guys look scary. But then, don't most other beings packing weapons?}
		cycleSpeed 4
		moveSpeed 4
		firePosn 0
		SLastCel 0
		stepState 1
		stepVol 60
	)
	
	(method (init)
		(super init: &rest)
		(self
			setCycle: StopWalk 480
			illegalBits: 0
			setLoop: GradualLooper
			x: 337
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
			(and
				(== view 410)
				(!= SLastCel cel)
				(or
					(and (== (self loop?) 0) (OneOf (self cel?) 2 6))
					(and (== (self loop?) 1) (OneOf (self cel?) 2 6))
					(and (== (self loop?) 2) (OneOf (self cel?) 2 5))
					(and (== (self loop?) 4) (OneOf (self cel?) 1 5))
					(and (== (self loop?) 5) (OneOf (self cel?) 1 5))
					(and (== (self loop?) 6) (OneOf (self cel?) 3 6))
					(and (== (self loop?) 7) (OneOf (self cel?) 3 6))
				)
			)
			(if (<= x 0)
				(= temp0 0)
			else
				(if (> (= temp0 (/ x 2)) 127) (= temp0 127))
				(if (< temp0 0) (= temp0 0))
			)
			(sarienSteps number: 313 loop: 1)
			(if
			(and (or (> x 320) (< x 0)) (OneOf stepState 1 2))
				(cond 
					((== stepState 1)
						(if (> (= stepVol (+ stepVol 10)) 127)
							(= stepVol 127)
						)
					)
					((< (= stepVol (- stepVol 10)) 0) (= stepVol 0))
				)
			else
				(= stepVol 127)
			)
			(sarienSteps play: stepVol send: 2 10 temp0)
		)
		(if
			(or
				(and
					(== view 411)
					(or
						(and (<= loop 3) (== cel 5))
						(and (> loop 3) (== cel 6))
					)
				)
				(and
					(== view 412)
					(or
						(and (<= loop 1) (== cel 4))
						(and (> loop 1) (== cel 5))
					)
				)
				(and (== view 413) (== cel 5))
			)
			(soundFx number: 312 loop: 1 play:)
		)
		(= SLastCel cel)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo (Print 700 0))
			(verbSmell (Print 700 1))
			(verbTaste (Print 700 1))
			(verbLook
				(if (== currentFloor sarienFloor)
					(super doVerb: theVerb &rest)
				else
					(Print 700 2)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class arcadaRegion of Region
	(properties
		rFlag1 0
		rFlag2 0
		timeTilSariens -1
		sarienState 0
		safeCode 0
		checkFootCode 0
		checkEntryCode 0
		sarienEntryDir 0
		s1startX 337
		s1startY 0
		s2startX 337
		s2startY 0
		s1gotoX 0
		s1gotoY 0
		s2gotoX 0
		s2gotoY 0
		inGame 0
		explosionTimer 400
		cartNumber 0
		theTime 0
		lastTime 0
		theTimeID 0
		saveBits 0
	)
	
	(method (init &tmp temp0)
		(Load SOUND 308 364 365)
		(super init: &rest)
		(if (and inGame (!= curRoomNum 103))
			(detonationBox init:)
			(cond 
				((> selfDestructTimer 600) (Load SOUND 997))
				((> selfDestructTimer 300) (Load SOUND 996))
				((> selfDestructTimer 240) (Load SOUND 995))
				((> selfDestructTimer 180) (Load SOUND 994))
				((> selfDestructTimer 120) (Load SOUND 993))
				((> selfDestructTimer 60) (Load SOUND 992))
				((> selfDestructTimer 30) (LoadMany SOUND 991 990 989))
				((> selfDestructTimer 10) (LoadMany SOUND 984 985 986 987 988))
			)
		)
		(if
			(and
				inGame
				(!= (theMusic number?) 355)
				(or
					(and
						(OneOf curRoomNum 8 9 10 12)
						(!= (theMusic number?) 360)
						(= temp0 360)
					)
					(and
						(OneOf curRoomNum 3 4 5 6 103)
						(!= (theMusic number?) 300)
						(= temp0 300)
					)
				)
			)
			(theMusic
				number: temp0
				loop: -1
				hold: 0
				play: 30
				fade: 127 25 10 0
			)
			(if (== temp0 360) (theMusic hold: TRUE))
		)
		(if (OneOf curRoomNum 4 5 6 8 10 11)
			(Load SOUND 315)
			(UpperElevFeat init:)
			(LowElevFeat init:)
		)
		(if
			(and
				(> selfDestructTimer 60)
				(not (Btst fWearingSpacesuit))
				(& rFlag1 $0004)
				(OneOf curRoomNum 4 6 9)
				(not (Btst fWearingSpacesuit))
				(!= prevRoomNum 10)
			)
			(LoadMany VIEW 410 480)
			(Load SOUND 313)
			(if (== sarienEntryDir 1)
				(= s1startX (- s1startX 356))
				(= s2startX (- s2startX 356))
			)
			(if (>= howFast 1)
				(sarien1 init: x: (self s1startX?) hide: firePosn: 0)
			)
			(sarien2 init: x: (self s2startX?) hide: firePosn: 4)
			(= sarienFloor currentFloor)
			(if (!= prevRoomNum 7)
				(= sarienState 2)
				(self setCountDown: (Random 3 5))
			)
		else
			(= sarienState 1)
			(= sarienFloor 0)
			(= timeTilSariens -1)
		)
	)
	
	(method (doit &tmp [str 10])
		(super doit:)
		(cond 
			(
			(and (not local2) inGame (>= selfDestructTimer 0))
				(if (!= lastTime (= theTime (GetTime SYSTIME1)))
					(= lastTime theTime)
					(if (cast contains: detonationBox)
						(if theTimeID (Display 700 3 p_restore theTimeID))
						(= theTimeID
							(Display
								(Format @str 700 4
									(/ selfDestructTimer 60)
									(mod selfDestructTimer 60)
								)
								p_at (detonationBox x?) (- (detonationBox y?) (detonationBox z?))
								p_color myTextColor6
								p_font 2
								p_save
							)
						)
					)
					(if
						(OneOf selfDestructTimer
							1 2 3 4 5 10 15 30 60 120
							180 240 300 600 900 1800
						)
						(ego setScript: DoCountDown 0 selfDestructTimer)
					)
					(if (< (-- selfDestructTimer) 0)
						(= inGame 0)
						(theMusic fade:)
						(curRoom setScript: timesUp)
					)
					(if (!= timeTilSariens -1) (-- timeTilSariens))
					(if
						(and
							(!= curRoomNum 103)
							(not (cast contains: detonationBox))
						)
						(detonationBox init:)
					)
				)
			)
			((cast contains: detonationBox) (detonationBox dispose:))
		)
		(if
		(and explosionTimer inGame (not (-- explosionTimer)))
			(switch (Random 0 2)
				(0 (explosionSound number: 308))
				(1 (explosionSound number: 364))
				(2 (explosionSound number: 365))
			)
			(explosionSound loop: 1 play:)
			(ShakeScreen 6 (Random 1 3))
			(= explosionTimer (Random 50 1000))
		)
		(if (and (not script) (!= sarienState 1))
			(switch sarienState
				(2
					(if
						(or
							(and checkFootCode (self perform: checkFootCode))
							(== timeTilSariens -1)
						)
						(Print 700 5)
						(sarien2 cycleSpeed: 5 stepState: 1 setCycle: Forward)
						(self setCountDown: (Random 3 6))
						(= sarienState 3)
					)
				)
				(3
					(if
						(or
							(and checkEntryCode (self perform: checkEntryCode))
							(== timeTilSariens -1)
						)
						(self setScript: callGuards)
					)
				)
				(4
					(if (self perform: safeCode)
						(if (== timeTilSariens -1)
							(if
								(and
									(== curRoomNum 6)
									(!= currentFloor sarienFloor)
									(or
										(and (== sarienFloor 2) (< (ego x?) (+ s2gotoX 5)))
										(and (== sarienFloor 1) (> (ego x?) (- s2gotoX 5)))
									)
								)
								0
							else
								(= sarienState 5)
							)
						)
					else
						(self setScript: shootEgoDead)
					)
				)
				(5
					(= sarienState 1)
					(self setScript: removeGuards)
				)
			)
		)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 3 4 5 6 7 8 9 10 11 12 13 103))
		(= safeCode
			(= sarienState (= sarienFloor (= initialized 0)))
		)
		(= timeTilSariens -1)
		(if script (script dispose:))
		(self
			sarienEntryDir: 0
			s1startX: 337
			s2startX: 337
			s1startY: 0
			s2startY: 0
			s1gotoX: 0
			s1gotoY: 0
			s2gotoX: 0
			s2gotoY: 0
			checkFootCode: 0
			checkEntryCode: 0
		)
		(if
			(and
				(!= (theMusic number?) 355)
				(or (OneOf n 7 11 13) (== n 7))
			)
			(theMusic loop: 0 fade: number: 0)
		)
		(if theTimeID
			(Display 700 3 p_restore theTimeID)
			(Animate (cast elements?) FALSE)
			(= theTimeID 0)
		)
		(super newRoom: n &rest)
	)
	
	(method (setCountDown newTime)
		(if (not (HaveMouse))
			(= newTime (+ newTime 1))
		)
		(= timeTilSariens newTime)
	)
)

(instance callGuards of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== curRoomNum 4) (== sarienFloor 2))
			(if (cast contains: sarien1) (sarien1 setPri: 3))
			(sarien2 setPri: 3)
		else
			(if (cast contains: sarien1) (sarien1 setPri: -1))
			(sarien2 setPri: -1)
		)
		(cond 
			((== state 0)
				(switch (arcadaRegion sarienEntryDir?)
					(1
						(if
							(or
								(!= sarienFloor currentFloor)
								(> (ego x?) 160)
								(and (== curRoomNum 9) (<= (ego y?) 109))
							)
							(self cue:)
						)
					)
					(2
						(if
						(or (!= sarienFloor currentFloor) (< (ego x?) 160))
							(self cue:)
						)
					)
				)
			)
			((== sarienFloor currentFloor)
				(switch (arcadaRegion sarienEntryDir?)
					(1
						(if (and (< (ego x?) 160) (> (sarien2 x?) 10))
							(if (and (== curRoomNum 9) (< (ego y?) 109))
								0
							else
								(if (cast contains: sarien1) (sarien1 setMotion: 0))
								(sarien2 setMotion: 0)
								(arcadaRegion setCountDown: 0 sarienState: 4)
								(self dispose:)
							)
						)
					)
					(2
						(if (and (> (ego x?) 160) (< (sarien1 x?) 310))
							(if (cast contains: sarien1) (sarien1 setMotion: 0))
							(sarien2 setMotion: 0)
							(arcadaRegion setCountDown: 0 sarienState: 4)
							(self dispose:)
						)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(arcadaRegion sarienEntryDir: 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(if (cast contains: sarien1)
					(sarien1 show: y: (arcadaRegion s1startY?))
				)
				(sarien2 show: cycleSpeed: 4 y: (arcadaRegion s2startY?))
				(= cycles 3)
			)
			(2
				(if (cast contains: sarien1)
					(sarien1
						stepState: 3
						setMotion: MoveTo (arcadaRegion s1gotoX?) (arcadaRegion s1gotoY?) self
					)
				else
					(= cycles 1)
				)
				(sarien2
					stepState: 3
					setCycle: StopWalk 480
					setMotion: MoveTo (arcadaRegion s2gotoX?) (arcadaRegion s2gotoY?) self
				)
			)
			(3 0)
			(4
				(arcadaRegion setCountDown: 2 sarienState: 4)
				(self dispose:)
			)
		)
	)
)

(instance removeGuards of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (> (sarien2 heading?) 180)
					(if (cast contains: sarien1) (sarien1 setHeading: 90))
					(sarien2 setHeading: 90 self)
				else
					(if (cast contains: sarien1) (sarien1 setHeading: 270))
					(sarien2 setHeading: 270 self)
				)
			)
			(1
				(if (cast contains: sarien1)
					(Print 700 6)
				else
					(Print 700 7)
				)
				(if (cast contains: sarien1)
					(sarien1
						setMotion: MoveTo (arcadaRegion s1startX?) (arcadaRegion s1startY?)
					)
				)
				(= cycles 2)
			)
			(2
				(sarien2
					stepState: 2
					setMotion: MoveTo (arcadaRegion s2startX?) (arcadaRegion s2startY?) self
				)
			)
			(3
				(if (cast contains: sarien1) (sarien1 dispose:))
				(sarien2 cycleSpeed: 5 setCycle: Forward)
				(= seconds 2)
			)
			(4
				(sarien2 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance shootEgoDead of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (not (curRoom script?)))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 0)
			(1
				(if (not (self perform: (arcadaRegion safeCode?)))
					(= local2 1)
					(HandsOff)
					(ego setMotion: 0)
					(if (= temp0 (cast firstTrue: #isKindOf Elevator))
						(temp0 state: 0)
					)
					(Face ego sarien2)
					(Face sarien2 ego self)
					(if (cast contains: sarien1)
						(Face ego sarien1 self)
						(Face sarien1 ego)
					else
						(= cycles 2)
					)
				else
					(arcadaRegion setCountDown: 2)
					(self dispose:)
				)
			)
			(2 0)
			(3
				(if (cast contains: sarien1)
					(sarien1
						view:
							(cond 
								(
									(or
										(and
											(>= 105 (sarien1 heading?))
											(>= (sarien1 heading?) 75)
										)
										(and
											(>= 285 (sarien1 heading?))
											(>= (sarien1 heading?) 255)
										)
									)
									411
								)
								(
									(and
										(> 255 (sarien1 heading?))
										(> (sarien1 heading?) 75)
									)
									412
								)
								(else 413)
							)
						setLoop: (+ (sarien1 firePosn?) (>= (sarien1 heading?) 180))
						cel: 0
						setCycle: Oscillate 1
					)
				)
				(sarien2
					view:
						(cond 
							(
								(or
									(and
										(>= 105 (sarien2 heading?))
										(>= (sarien2 heading?) 75)
									)
									(and
										(>= 285 (sarien2 heading?))
										(>= (sarien2 heading?) 255)
									)
								)
								411
							)
							(
								(and
									(>= 255 (sarien2 heading?))
									(>= (sarien2 heading?) 75)
								)
								412
							)
							(else 413)
						)
					setLoop: (+ (sarien2 firePosn?) (>= (sarien2 heading?) 180))
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(if (== (sarien2 view?) 411)
					(sarien2 setCycle: CycleTo 5 -1)
				else
					(sarien2 setCycle: CycleTo 4 -1)
				)
				(ego
					view: 6
					cel: 0
					setLoop:
						(cond 
							((& (ego onControl:) cGREY) 2)
							((>= (ego heading?) 180) 1)
							(else 0)
						)
					illegalBits: 0
					ignoreActors: 1
					setCycle: CycleTo 3 1 self
				)
			)
			(5
				(cond 
					((& (ego onControl:) cLGREY) (ego setLoop: 0))
					((& (ego onControl:) cYELLOW) (ego setLoop: 3))
				)
				(ego cel: 3 setCycle: EndLoop self)
			)
			(6
				(if (cast contains: sarien1)
					(Print 700 8)
				else
					(Print 700 9)
				)
				(sarien2 setCycle: BegLoop self)
			)
			(7
				(if (Btst fWearingSpacesuit)
					(EgoDead 940 1 0 700 10)
				else
					(EgoDead 940 0 0 700 10)
				)
			)
		)
	)
)

(instance timesUp of Script
	(properties)
	
	(method (init)
		(LoadMany SOUND 804 805)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (and local1 (< (deltaur y?) -25))
			(= local1 0)
			(= cycles 1)
		)
		(if
			(and
				local0
				(== (theMusic number?) 355)
				(== (theMusic prevSignal?) -1)
			)
			(= local0 0)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (cast contains: detonationBox)
					(detonationBox dispose:)
				)
				(sampledVoice stop:)
				(curRoom drawPic: 16 FADEOUT)
				(cast eachElementDo: #dispose)
				(deltaurArm init:)
				(arcada init:)
				(deltaur init:)
				(star1 init: setCycle: Forward)
				(star2 init: setCycle: Forward)
				(star3 init: setCycle: Forward)
				(= seconds 4)
			)
			(1
				(soundFx number: 804 loop: 1 play:)
				(deltaur
					illegalBits: 0
					setMotion: MoveTo (- (deltaur x?) 50) (- (deltaur y?) 120)
				)
				(deltaurArm
					setMotion: MoveTo (- (deltaurArm x?) 50) (- (deltaurArm y?) 120)
					setCycle: EndLoop
				)
				(= local1 1)
			)
			(2 (= local0 1))
			(3
				(theMusic2 number: 805 loop: 1 flags: 1 play:)
				(arcada setCycle: EndLoop self)
			)
			(4
				(arcada dispose:)
				(= cycles 12)
			)
			(5
				(Print 700 11)
				(Print 700 12)
				(Print 700 13)
				(EgoDead 945 0 0 700 14)
			)
		)
	)
)

(instance DoCountDown of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(cond 
			((> selfDestructTimer 600) (Load SOUND 997))
			((> selfDestructTimer 300) (UnLoad SOUND 997) (Load SOUND 996))
			((> selfDestructTimer 240) (UnLoad SOUND 996) (Load SOUND 995))
			((> selfDestructTimer 180) (UnLoad SOUND 995) (Load SOUND 994))
			((> selfDestructTimer 120) (UnLoad SOUND 994) (Load SOUND 993))
			((> selfDestructTimer 60) (UnLoad SOUND 993) (Load SOUND 992))
			((> selfDestructTimer 30) (UnLoad SOUND 992) (LoadMany SOUND 991 990 989))
			((> selfDestructTimer 10) (LoadMany SOUND 984 985 986 987 988))
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register 30)
					(theMusic number: 355 loop: 1 flags: 0 play:)
				)
				(sampledVoice
					number:
						(switch register
							(1 984)
							(2 985)
							(3 986)
							(4 987)
							(5 988)
							(10 989)
							(15 990)
							(30 991)
							(60 992)
							(120 993)
							(180 994)
							(240 995)
							(300 996)
							(600 997)
							(900 998)
							(1800 982)
						)
					loop: 1
					play: self
				)
			)
			(1
				(if (> register 5)
					(sampledVoice number: 983 loop: 1 play: self)
				else
					(self dispose:)
				)
			)
			(2
				(if
					(or
						(and howFast (== register 900))
						(and (not howFast) (== register 1800))
					)
					((ScriptID 4 1) cue:)
				)
				(if
				(and (!= curRoomNum 13) (< selfDestructTimer 15))
					(arcadaRegion inGame: 0)
					(curRoom setScript: timesUp)
				)
				(self dispose:)
			)
		)
	)
)

(instance star1 of Prop
	(properties
		x 155
		y 51
		view 216
		loop 4
		cel 3
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 20
	)
)

(instance star2 of Prop
	(properties
		x 271
		y 114
		view 216
		loop 4
		cel 1
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 24
	)
)

(instance star3 of Prop
	(properties
		x 105
		y 154
		view 216
		loop 4
		cel 2
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 16
	)
)

(instance arcada of Prop
	(properties
		x 247
		y 100
		view 216
		signal (| ignrAct ignrHrz fixedCel) ;$6800
		cycleSpeed 5
	)
)

(instance detonationBox of View
	(properties
		z 29
		description {countdown window}
		view 3
		priority 15
		signal (| ignrAct ignrHrz fixPriOn stopUpdOn) ;$6011
	)
	
	(method (init)
		(super init: &rest)
		(DisplayDetonationBox)
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(and (< x 160) (< (ego x?) 160))
				(and (> x 160) (> (ego x?) 160))
			)
			(DisplayDetonationBox)
		)
	)
	
	(method (dispose)
		(if (arcadaRegion theTimeID?)
			(Display 700 3 108 (arcadaRegion theTimeID?))
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp [str 50])
		(switch theVerb
			(verbLook
				(Print
					(Format @str 700 15
						(/ (+ selfDestructTimer 1) 60)
						(mod (+ selfDestructTimer 1) 60)
					)
					#mode teJustCenter
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sarien1 of Sarien
	(properties
		x 337
		view 410
	)
)

(instance sarien2 of Sarien
	(properties
		x 337
		view 410
	)
)

(instance deltaur of Actor
	(properties
		x 253
		y 49
		view 216
		loop 1
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 4
		xStep 1
		moveSpeed 4
	)
)

(instance deltaurArm of Actor
	(properties
		x 253
		y 49
		view 216
		loop 2
		priority 12
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn) ;$7810
		cycleSpeed 4
		moveSpeed 4
	)
)

(instance UpperElevFeat of Feature
	(properties
		description {elevator shaft}
		sightAngle 45
		onMeCheck $0020
		lookStr {This is an elevator shaft. Elevators are known to have traveled these parts, mostly up and down.}
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(Print 700 16)
		)
	)
)

(instance LowElevFeat of Feature
	(properties
		description {elevator shaft}
		sightAngle 45
		onMeCheck $2000
		lookStr {This is an elevator shaft.}
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 700 16)
		)
	)
)

(instance sarienSteps of Sound
	(properties)
)

(instance explosionSound of Sound
	(properties
		number 308
	)
)

(instance sampledVoice of Sound
	(properties)
)
