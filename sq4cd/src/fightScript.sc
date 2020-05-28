;;; Sierra Script 1.0 - (do not remove this comment)
(script# 151)
(include game.sh)
(use Main)
(use brain)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use LoadMany)
(use StopWalk)
(use Reverse)
(use Motion)
(use System)

(public
	fightScript 0
	endFightScript 1
)

(local
	local0
	local1
	local2
	local3 =  50
	local4
	local5
	local6
	[local7 8] = [161 189 186 154 120 91 89 118]
	[local15 8] = [119 107 80 67 61 79 107 126]
	[local23 10] = [361 353 307 267 246 173 126 85 64]
	[local33 9] = [5 4 3 2 1 0 7 6 5]
	[local42 9] = [1 0 7 6 5 4 3 2 1]
	[local51 8] = [2 2 1 1 3 3]
	[local59 8] = [6 6 5 5 7 7 4 4]
	[local67 8] = [5 -5 0 0 -5 5 5]
	[local75 8] = [5 5 0 -5 -5 -5]
)
(procedure (localproc_0038 param1 param2)
	(if (not ((ScriptID 150 1) mover?))
		(ego view: 526 setCycle: Walk)
		((ScriptID 150 1) view: 527 setCycle: Walk)
		(cond 
			((== param1 0) (if (> (++ local5) 7) (= local5 0)))
			((< (-- local5) 0) (= local5 7))
		)
		(param2 cue:)
	)
)

(instance fightScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(directionHandler addToFront: self)
		(mouseDownHandler
			addToFront: self
			addToFront: (ScriptID 150 1)
		)
		(= local1 1)
		(= local5 0)
		(= local6 4)
		(LoadMany VIEW 523 524 525 526 527 528)
		(music number: 868 vol: 127 loop: -1 play:)
		(theGame setCursor: normalCursor TRUE)
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(cond 
			(script)
			(
				(and
					(< (ego distanceTo: (ScriptID 150 1)) 15)
					(< state 4)
				)
				(self setScript: struggleScript self)
			)
			((== (ego view?) 526)
				(if (ego mover?)
					(= temp0 (GetAngle (ego x?) (ego y?) 137 89))
					(= temp1 0)
					(while (< temp0 [local23 temp1])
						(++ temp1)
					)
					(-- temp1)
					(ego setLoop: [local33 temp1])
					((ScriptID 150 1) setLoop: [local42 temp1])
				)
				(if (< (-- local3) 1)
					(= register 0)
					(self changeState: 3)
				)
			)
		)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(mouseDownHandler delete: self delete: (ScriptID 150 1))
		(super dispose:)
		(DisposeScript (ScriptID 151))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 526 setLoop: 3)
				(= cycles 1)
			)
			(1
				(= local0 1)
				(theGame setCursor: normalCursor TRUE)
				(curRoom notify: local0)
			)
			(2
				((ScriptID 150 1)
					setMotion:
						MoveTo
						[local7 local6]
						[local15 (ego
							setMotion:
								MoveTo
								[local7 local5]
								[local15 (if (> (= local6 (+ local5 4)) 7)
									(= local6 (- local6 8))
								)]
						)]
				)
				(self changeState: 1)
			)
			(3
				(if register
					(ego
						view: 524
						setMotion: MoveTo ((ScriptID 150 1) x?) ((ScriptID 150 1) y?)
					)
				)
				((ScriptID 150 1)
					view: 525
					setMotion: MoveTo (ego x?) (ego y?)
				)
			)
			(4
				(if (== local4 5)
					(= cycles 1)
				else
					(= local3 (Random 50 100))
					(self changeState: 1)
				)
			)
			(5 (self dispose:))
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if local0
			(cond 
				((== (event type?) mouseDown)
					(= temp0 (OnControl cGREEN (event x?) (event y?)))
					(= temp1 (OnControl cGREEN (ego x?) (ego y?)))
					(cond 
						(
							(or
								(== temp0 (* temp1 2))
								(and (== temp0 8) (== temp1 1024))
							)
							(localproc_0038 0 self)
						)
						(
							(or
								(== temp0 (/ temp1 2))
								(and (== temp0 1024) (== temp1 8))
							)
							(localproc_0038 1 self)
						)
					)
				)
				(
					(and
						(& (event type?) direction)
						(== (ego view?) 526)
						(not (ego mover?))
					)
					(switch (event message?)
						(dirN
							(= register 1)
							(self changeState: 3)
							(= local1 0)
						)
						(dirE
							(localproc_0038 0 self)
						)
						(dirW
							(localproc_0038 1 self)
						)
					)
				)
			)
		)
	)
)

(instance struggleScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(= local0 0)
				(curRoom notify: local0)
				(theGame setCursor: (ScriptID 0 19) 1)
				(= temp0 (+ (ego x?) [local67 (ego loop?)]))
				(= temp1 (+ (ego y?) [local75 (ego loop?)]))
				(ego
					view: 523
					posn: temp0 temp1
					setCel: 0
					setCycle: CycleTo (Random 3 7) 1 self
					setMotion: 0
				)
				((ScriptID 150 1) setMotion: 0 hide:)
			)
			(1 (= cycles (Random 1 5)))
			(2
				(ego setCycle: CycleTo (Random 0 2) -1 self)
			)
			(3
				(if (== (++ local4) 5)
					(ego setLoop: 4)
					((ScriptID 150 1) setLoop: 0)
					(= local5 0)
					(= local6 4)
				)
				(= cycles (Random 1 5))
			)
			(4
				(ego setCycle: CycleTo (Random 3 7) 1 self)
			)
			(5
				(= register (if (== local4 5) 0 else (Random 0 1)))
				(= temp0 (- (ego x?) [local67 (ego loop?)]))
				(= temp1 (- (ego y?) [local75 (ego loop?)]))
				(if register
					((ScriptID 150 1)
						view: 525
						setLoop: [local42 (+ local5 1)]
						show:
					)
					(ego
						view: 528
						posn: temp0 temp1
						setLoop: [local51 (ego loop?)]
						setCel: 0
						cycleSpeed: 12
						setCycle: CycleTo 2 1 self
					)
				else
					(ego
						view: 524
						setLoop: (if (== local4 5) 4 else [local33 (+ local5 1)])
					)
					((ScriptID 150 1)
						view: 528
						setLoop: [local59 ((ScriptID 150 1) loop?)]
						setCel: 0
						show:
						cycleSpeed: 12
						setCycle: CycleTo 2 1 self
					)
				)
			)
			(6
				(if local1
					(if register
						(if local2
							(directionHandler delete: client)
							(mouseDownHandler
								delete: client
								delete: (ScriptID 150 1)
							)
							(client setScript: fallScript)
						else
							(= local2 1)
							(narrator modNum: 151 say: 1 self)
						)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(7
				(if register
					((ScriptID 150 1)
						view: 525
						setLoop: [local42 (+ local5 1)]
						show:
						setCycle: Reverse
						setMotion: MoveTo [local7 local6] [local15 local6] self
					)
				else
					(= temp2 (if (== local4 5) 0 else local5))
					(ego
						view: 524
						setLoop: (if (== local4 5) 4 else [local33 (+ local5 1)])
						setCycle: Reverse
						setMotion: MoveTo [local7 temp2] [local15 temp2] self
					)
				)
			)
			(8
				(if register
					((ScriptID 150 1)
						view: 525
						setLoop: [local42 (+ local5 1)]
						setCycle: 0
					)
					(ego setCycle: EndLoop self)
				else
					(ego
						view: 524
						setLoop: (if (== local4 5) 4 else [local33 (+ local5 1)])
						setCycle: 0
					)
					((ScriptID 150 1) setCycle: EndLoop self)
				)
			)
			(9
				(if register
					(ego
						view: 524
						setLoop: [local33 (+ local5 1)]
						cycleSpeed: 6
						setCycle: Reverse
						setMotion:
							MoveTo
							[local7 local5]
							[local15 ((ScriptID 150 1) view: 527 setCycle: Walk)]
							self
					)
				else
					(ego view: 526 setCycle: Walk)
					((ScriptID 150 1)
						view: 525
						setLoop: [local42 (+ local5 1)]
						cycleSpeed: 6
						setMotion: 0
					)
					(if (not (== local4 5))
						((ScriptID 150 1)
							setCycle: Reverse
							setMotion: MoveTo [local7 local6] [local15 local6] self
						)
					else
						((ScriptID 150 1)
							setCycle: Walk
							setMotion: MoveTo 145 105 self
						)
					)
				)
			)
			(10
				(if (not (== local4 5))
					(if register
						(ego view: 526 setCycle: Walk)
					else
						((ScriptID 150 1) view: 527 setCycle: Walk)
					)
					(= local1 1)
				)
				(theGame setCursor: normalCursor 1)
				(self dispose:)
			)
		)
	)
)

(instance endFightScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 150 2)
					setMotion: MoveTo 120 98 (ScriptID 150 2)
				)
				(= local3 1000)
			)
			(1
				(globalSound number: 848 vol: 127 loop: -1 play:)
				(beam init: setCycle: Forward)
				(brain
					makePolygon:
						127 101 121 103 112 96 111 91 111 87 113 80 115 2
						169 2 162 81 160 88 158 95 149 100 142 102
				)
				(= cycles 6)
			)
			(2
				((ScriptID 150 1)
					setCycle: Walk
					setMotion: MoveTo 145 105 self
				)
			)
			(3 (tVOHAULJR say: 1 self))
			(4
				(ego
					setCycle: Walk
					setMotion:
						PolyPath
						((ScriptID 150 1) x?)
						(+ ((ScriptID 150 1) y?) 7)
						self
				)
			)
			(5
				((ScriptID 150 1) hide:)
				(ego view: 523 setCycle: Forward)
				(= seconds 2)
			)
			(6
				(ego view: 524 setCycle: Walk)
				((ScriptID 150 1)
					view: 528
					posn: 139 91
					setLoop: 6
					setCel: 0
					show:
					cycleSpeed: 12
					setCycle: CycleTo 2 1 self
				)
			)
			(7
				((ScriptID 150 1)
					view: 522
					setLoop: 1
					setCel: 5
					setCycle: BegLoop self
				)
			)
			(8
				((ScriptID 150 1)
					setLoop: 0
					setPri: 4
					posn: 127 39
					cycleSpeed: 6
					setCycle: Forward
				)
				(beam dispose:)
				(globalSound stop:)
				(ego
					view: 3
					setCycle: StopWalk 510
					headView: 510
					setLoop: -1
					setPri: -1
					normal: 1
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cond 
					((< (ego y?) 95) (= temp0 1))
					((< (ego x?) 95) (= temp0 12))
					(else (= temp0 3))
				)
				(ego
					setStep: (ego xStep?) 19
					setPri: temp0
					setMotion: MoveTo (ego x?) 300 self
				)
			)
			(1 (EgoDead))
		)
	)
)

(instance beam of Sq4Prop
	(properties
		x 127
		y 39
		view 522
		loop 5
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance tVOHAULJR of Sq4Talker
	(properties
		z 400
		noun VOHAULJR
		modNum 151
		view 1523
		talkerNum VOHAULJR
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 20
	)
)
