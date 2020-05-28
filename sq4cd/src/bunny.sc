;;; Sierra Script 1.0 - (do not remove this comment)
(script# BUNNY) ;705
(include game.sh)
(use Main)
(use Sq4Feature)
(use PolyPath)
(use Sound)
(use Motion)
(use Game)
(use User)
(use System)

(public
	bunny 0
	theBunny 1
	ropeScript 2
	tempHead 3
	theRoom 4
)

(local
	local0
	local1
	local2
	theBunnyLoop
	[local4 8] = [4 7 5 6 2 1 0 3]
	[bunnyPosn 180] = [69 126 89 163 88 210 0 40 0 329 101 286 101 286 210 2 40 30 1 213 -10 188 94 188 -10 188 4 25 0 320 188 236 188 320 188 2 35 0 1 160 29 210 29 88 -10 88 4 30 0 315 169 220 166 220 210 0 50 0 1 90 329 54 263 54 301 -2 2 25 45 172 210 219 145 280 210 3 55 0 0 110 -10 155 94 155 -10 155 4 40 0 320 160 236 158 320 160 2 50 0 1 160 -10 50 67 40 52 -2 0 35 0 118 210 98 160 50 210 0 65 0 0 110 306 -2 265 54 329 54 2 60 0 329 180 276 166 329 97 2 60 0 0 92 -10 155 94 155 -10 155 4 55 0 320 160 236 158 320 160 2 65 0 1 160 0 -2 29 71 -10 71 4 60 0 -10 130 36 176 -10 176 4 60 0 0 109]
	[bunnyPoly 63] = [245 138 188 167 2 30 3 96 185 242 188 4 25 2 73 121 104 154 4 30 3 214 165 255 122 3 55 2 68 166 250 166 4 40 2 196 168 70 115 3 65 4 295 35 250 107 1 40 2 87 132 256 132 4 55 2 14 44 65 148 1 50 4]
	[ropePoly 18] = [76 160 0 0 188 147 171 142 0 0 143 140 210 50 0 0 105 69]
	[snarePoly 36] = [123 210 154 164 185 210 118 210 111 152 50 210 219 210 249 142 274 210 78 210 66 146 20 210 327 20 287 52 327 52 -7 27 29 73 -7 73]
)
(class bunny of Region
	(properties
		bunnyWait 300
		bunnyRoom 0
		followTimer 0
		followFlag 0
	)
	
	(method (init)
		(Load VIEW 105)
		(Lock VIEW 105 1)
		(Load SOUND 116)
		(Lock SOUND 116 1)
		(super init:)
		(if (and followFlag (not (ego has: iRabbit)))
			(theBunny setCycle: Walk init: setScript: buzzOff)
		)
		(theRoom init:)
	)
	
	(method (doit)
		(super doit:)
		(theBunny setSpeed: speed)
		(cond 
			((curRoom script?) 0)
			((> followTimer 0) (= followTimer (- followTimer 1)))
			(
			(and (> bunnyWait 0) (not (theBunny script?)))
				(= bunnyWait (- bunnyWait 1))
				(if (and (not (ego has: iRabbit)) (== bunnyWait 60))
					(theBunny init: z: 1000 setCycle: Forward)
				)
			)
			(
				(and
					(not (theBunny script?))
					(== ((inventory at: 3) owner?) 0)
				)
				(self bunnyWait: 300)
				(theBunny
					z: 0
					setCycle: Walk
					setScript: bunnyScript
					init:
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= keep (OneOf newRoomNumber 25 30 35 40 45 50 55 60 65))
		(= initialized FALSE)
		(theBunny cel: 0)
		(if
		(and (== (self bunnyRoom?) newRoomNumber) followTimer)
			(self followFlag: 1)
		else
			(self followFlag: 0)
		)
		(super newRoom: newRoomNumber &rest)
	)
)

(instance bunnyScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (< register 22) (++ register))
		(if
			(or
				(and
					(not (& (ego onControl: origin) cGREY))
					(not (& (ego onControl: origin) cLRED))
					(== (curRoom script?) ropeScript)
					local2
				)
				(and (> register 20) (ego mover?) local2)
			)
			(theBunny setScript: runAway)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					(
						[bunnyPosn (+ (= local1 (* (- (/ curRoomNum 5) 5) 20)) 18)]
						(if (< (ego x?) [bunnyPosn (+ local1 19)])
							(= local1 (+ local1 9))
						)
					)
					((< (ego y?) [bunnyPosn (+ local1 19)]) (= local1 (+ local1 9)))
				)
				(bunny bunnyRoom: [bunnyPosn (+ local1 7)])
				(= cycles 1)
			)
			(1
				(= local2 [bunnyPosn (+ local1 6)])
				(theBunny
					x: [bunnyPosn local1]
					y: [bunnyPosn (+ local2 1)]
					setMotion: MoveTo [bunnyPosn (+ local1 2)] [bunnyPosn (+ local1 3)] self
				)
			)
			(2
				(theBunny
					setMotion: MoveTo [bunnyPosn (+ local1 4)] [bunnyPosn (+ local1 5)] self
				)
			)
			(3
				(bunny followTimer: 60)
				(theBunny z: 1000 setCycle: Forward)
				(client setScript: 0)
			)
		)
	)
)

(instance runAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local2
					(1
						(theBunny setMotion: MoveTo (theBunny x?) -2 self)
					)
					(2
						(theBunny setMotion: MoveTo 329 (theBunny y?) self)
					)
					(3
						(theBunny setMotion: MoveTo (theBunny x?) 210 self)
					)
					(4
						(theBunny setMotion: MoveTo -10 (theBunny y?) self)
					)
				)
			)
			(1
				(if [bunnyPosn (+ local1 8)]
					(bunny bunnyRoom: [bunnyPosn (+ local1 8)])
				else
					(bunny bunnyRoom: [bunnyPosn (+ local1 7)])
				)
				(bunny followTimer: 60)
				(theBunny z: 1000 setCycle: Forward)
				(= local2 0)
				(client setScript: 0)
			)
		)
	)
)

(instance buzzOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (* (- (/ curRoomNum 5) 5) 7))
				(if (== [bunnyPoly (+ register 5)] prevRoomNum)
					(= register (+ register 2))
				)
				(self cue:)
			)
			(1
				(theBunny
					x: [bunnyPoly register]
					y: [bunnyPoly (+ register 1)]
					z: 0
					setCycle: Walk
					init:
				)
				(switch [bunnyPoly (+ register 4)]
					(1
						(theBunny setMotion: MoveTo (theBunny x?) -2 self)
					)
					(2
						(theBunny setMotion: MoveTo 329 (theBunny y?) self)
					)
					(3
						(theBunny setMotion: MoveTo (theBunny x?) 210 self)
					)
					(4
						(theBunny setMotion: MoveTo -10 (theBunny y?) self)
					)
				)
			)
			(2
				(bunny bunnyWait: 300 followTimer: 60 bunnyRoom: 0)
				(theBunny z: 1000 setCycle: Forward)
				(client setScript: 0)
			)
		)
	)
)

(instance ropeScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Load VIEW 23)
				(if
					(or
						(& (ego onControl: origin) cGREY)
						(& (ego onControl: origin) cLRED)
					)
					(= temp0 (* (- (/ curRoomNum 5) 5) 2))
					(ego
						setMotion: PolyPath [ropePoly temp0] [ropePoly (+ temp0 1)] self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(if
					(or
						(== curRoomNum 25)
						(== curRoomNum 40)
						(== curRoomNum 55)
						(and
							(< (ego x?) 160)
							(or
								(== curRoomNum 30)
								(== curRoomNum 45)
								(== curRoomNum 60)
							)
						)
					)
					(self register: 1)
				else
					(self register: 0)
				)
				(if (& (ego onControl: origin) cLGREY)
					(if register
						(= register 0)
						(ego setHeading: 270)
					else
						(= register 1)
						(ego setHeading: 90)
					)
				)
				(= cycles 4)
			)
			(2 (= cycles 1))
			(3
				(ego
					setMotion: 0
					normal: 0
					view: 23
					setLoop: (+ register 2)
					cel: 0
				)
				(theRope
					setLoop: register
					cel: 0
					x: (if register (- (ego x?) 6) else (+ (ego x?) 6))
					y: (- (ego y?) 23)
					z: 0
					cycleSpeed: 24
					setCycle: EndLoop
					init:
					setMotion:
						MoveTo
						(if register (+ (ego x?) 19) else (- (ego x?) 19))
						(- (ego y?) 3)
						self
				)
			)
			(4
				(ego cycleSpeed: 18 setCycle: CycleTo 2 1 self)
			)
			(5
				(ego setCycle: EndLoop)
				(= cycles 3)
			)
			(6
				(tempHead
					setLoop: (+ register 4)
					x: (if register (+ (ego x?) 9) else (- (ego x?) 9))
					y: (ego y?)
					cycleSpeed: 54
					setCycle: Forward
					init:
				)
				(if
					(or
						(& (ego onControl: origin) cLRED)
						(& (ego onControl: origin) cGREY)
					)
					(curRoom setScript: bunnySnare)
				else
					(= cycles 50)
				)
			)
			(7
				(if
				(!= ((ScriptID STREET 1) script?) (ScriptID STREET 4))
					(narrator modNum: BUNNY say: 1)
					(= cycles 1)
				)
			)
			(8
				(ropeScript start: 0)
				(theRope cycleSpeed: 6 setCycle: BegLoop self)
			)
			(9
				(ego setCel: 0)
				(theRope
					setCel: 0
					x: (if register (+ (ego x?) 11) else (- (ego x?) 11))
					y: (- (ego y?) 20)
					setMotion:
						MoveTo
						(if register (- (ego x?) 7) else (+ (ego x?) 7))
						(- (ego y?) 23)
						self
				)
			)
			(10
				(ropeScript start: 0)
				(theRope z: 1000)
				(tempHead dispose:)
				(NormalEgo (if register 0 else 1) 0)
				(= cycles 3)
			)
			(11
				(theRope dispose:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance bunnySnare of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (> (bunny bunnyWait?) 0)
			(bunny bunnyWait: (- (bunny bunnyWait?) 1))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: TRUE)
				(theIconBar enable: ICON_DO)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(= seconds 3)
			)
			(1
				(if (> (bunny bunnyWait?) 60) (bunny bunnyWait: 60))
				(theBunny z: 1000 setCycle: Forward init:)
				(= seconds 7)
			)
			(2
				(theBunny
					x: [snarePoly register]
					y:
						[snarePoly (+
							(= register
								(switch curRoomNum
									(25 0)
									(35 6)
									(40 12)
									(50 18)
									(55 24)
									(65 30)
								)
							)
							1
						)]
					z: 0
					setCycle: Walk
					init:
					setMotion:
						MoveTo
						[snarePoly (+ register 2)]
						[snarePoly (+ register 3)]
						self
				)
				(= theBunnyLoop (theBunny loop?))
			)
			(3
				(theBunny
					setCycle: Forward
					setLoop: [local4 (theBunny loop?)]
				)
				(if (!= (theBunny loop?) theBunnyLoop) (-- state))
				(= cycles 1)
			)
			(4
				(theBunny
					setLoop: -1
					setCycle: Walk
					setMotion:
						MoveTo
						[snarePoly (+ register 4)]
						[snarePoly (+ register 5)]
						self
				)
			)
			(5
				(HandsOff)
				(narrator modNum: BUNNY say: 2)
				(bunny bunnyWait: 300 followTimer: 60 bunnyRoom: 0)
				(theBunny z: 1000 setLoop: -1 setCycle: Forward)
				(ropeScript start: 8)
				(curRoom setScript: ropeScript)
			)
		)
	)
)

(instance catchBunny of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 835 loop: 1 vol: 127 play:)
				(theBunny setMotion: 0)
				(theRope
					cel: 0
					setLoop: (+ (theRope loop?) 6)
					cycleSpeed: 12
					setCycle: EndLoop
				)
				(= cycles 2)
			)
			(1
				(theBunny dispose:)
				(theRope setCycle: EndLoop self)
			)
			(2
				(theRope dispose:)
				(SolvePuzzle fCatchBunny 10)
				(narrator modNum: BUNNY say: 3 self)
				(ego get: iRabbit)
				(ego put: iRope 0)
			)
			(3
				(narrator modNum: BUNNY say: 4)
				(ropeScript start: 10)
				(curRoom setScript: ropeScript)
			)
		)
	)
)

(instance theBunny of Sq4Actor
	(properties
		view 105
		signal (| ignrAct ignrHrz)
		cycleSpeed 2
		illegalBits $0000
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
			(bunny bunnyWait: 300)
			(bunny followTimer: 0)
		)
		(switch (self cel?)
			(1 (= local0 0))
			(2
				(if
					(and
						(not local0)
						(!= (curRoom script?) catchBunny)
						(!= (curRoom script?) (ScriptID BUNNY 3))
						(== (self cel?) 2)
					)
					(if
						(= temp0
							(cond 
								((not (self z?)) 127)
								((bunny followTimer?) (* (bunny followTimer?) 2))
								((< (bunny bunnyWait?) 61) (* (Abs (- (bunny bunnyWait?) 60)) 2))
								(else 0)
							)
						)
						(drumBeat setVol: temp0 play:)
						(= local0 1)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BUNNY say: 5)
			)
			(V_DO
				(if
					(and
						(< 3 (- (theBunny y?) (theRope y?)))
						(< (- (theBunny y?) (theRope y?)) 12)
						(or
							(and
								(> 60 (- (theRope x?) (theBunny x?)))
								(> (- (theRope x?) (theBunny x?)) 54)
							)
							(and
								(> 60 (- (theBunny x?) (theRope x?)))
								(> (- (theBunny x?) (theRope x?)) 54)
							)
						)
					)
					(HandsOff)
					(curRoom setScript: catchBunny)
				else
					(narrator modNum: BUNNY say: 6)
				)
			)
			(V_TALK
				(narrator modNum: BUNNY say: 7)
			)
			(V_SMELL
				(narrator modNum: BUNNY say: 11)
			)
			(V_TASTE
				(narrator modNum: BUNNY say: 11)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRope of Sq4Actor
	(properties
		yStep 12
		view 23
		signal (| ignrAct ignrHrz)
		xStep 12
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self hide:)
		else
			(self show:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BUNNY say: 8)
			)
			(V_DO
				(cond 
					(
						(and
							(< 3 (- (theBunny y?) (theRope y?)))
							(< (- (theBunny y?) (theRope y?)) 12)
							(or
								(and
									(> 60 (- (theRope x?) (theBunny x?)))
									(> (- (theRope x?) (theBunny x?)) 54)
								)
								(and
									(> 60 (- (theBunny x?) (theRope x?)))
									(> (- (theBunny x?) (theRope x?)) 54)
								)
							)
						)
						(HandsOff)
						(curRoom setScript: catchBunny)
					)
					(
						(and
							(not ((ScriptID STREET 5) z?))
							(< (self distanceTo: (ScriptID STREET 5)) 60)
						)
						(narrator modNum: BUNNY say: 9)
					)
					(else (narrator modNum: BUNNY say: 10))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tempHead of Sq4Prop
	(properties
		z 33
		view 23
		signal ignrAct
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 31)
			(self z: 1000)
		else
			(self z: 33)
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROPE
				(cond 
					((!= ((inventory at: iRabbit) owner?) 0) 0)
					((Btst fPoliceLanded) (narrator modNum: BUNNY say: 11))
					((& (ego onControl: origin) cMAGENTA) (narrator modNum: BUNNY say: 12))
					((not (curRoom script?)) (HandsOff) (curRoom setScript: ropeScript))
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance drumBeat of Sound
	(properties
		number 116
	)
)
