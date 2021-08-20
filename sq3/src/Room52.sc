;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include game.sh)
(use Main)
(use Intrface)
(use Dune)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room52 0
)

(local
	local0
	egoY
	egoX
	local3
	local4
	blood
	theTheGGEgoX_5
	local7
	local8
)
(instance puss of Prop
	(properties)
)

(instance mouth of Prop
	(properties)
)

(instance tongue of Prop
	(properties)
)

(instance slurp of Sound
	(properties)
)

(instance Room52 of Room
	(properties
		picture 52
	)
	
	(method (init)
		(= north 48)
		(= west 51)
		(= east 49)
		(= south 53)
		(Load VIEW 78)
		(Load VIEW 122)
		(Load VIEW 777)
		(Load SOUND 82)
		(= global109 1)
		(= global591 1)
		(super init:)
		(= global116 0)
		(if (== roomWithDeadTerminator 52)
			((= terminatorRemains (View new:))
				view: 78
				setLoop: 8
				setCel: 255
				setPri: 5
				posn: 208 125
				init:
				stopUpd:
			)
		)
		(if (InRoom iInvisibilityBelt 52)
			((= theBelt (View new:))
				view: 120
				loop: 5
				cel: 0
				ignoreActors:
				posn: 209 128
				setPri: 6
				init:
				stopUpd:
			)
		)
		(NormalEgo)
		(puss
			view: 78
			loop: 0
			cel: 1
			posn: 204 50
			setPri: 7
			setCycle: Forward
			init:
		)
		(mouth
			view: 777
			ignoreActors:
			loop: 0
			cel: 0
			posn: 207 48
			setPri: 9
			init:
		)
		(tongue
			view: 777
			loop: 0
			cel: 0
			ignoreActors:
			posn: 207 59
			setPri: 10
			init:
		)
		(ego init:)
		(switch prevRoomNum
			(48
				(= global104 2)
				(ego posn: 119 70 view: 68 setPri: 1 ignoreControl: 8192)
			)
			(53
				(NormalEgo)
				(ego
					view: 0
					posn: (ego x?) 188
					observeControl: 8192 4096
				)
			)
			(51
				(switch global104
					(0
						(ego
							posn: 1 (ego y?)
							setStep: 3 2
							observeControl: 8192 4096
							setPri: -1
						)
					)
					(1
						(ego
							posn: 1 113
							setStep: 2 1
							view: 82
							ignoreControl: 8192
							observeControl: 4096
							looper: (ScriptID 501 1)
							setPri: 1
						)
					)
					(2
						(ego
							view: 68
							posn: 1 85
							looper: 0
							ignoreControl: 8192 4096
							setStep: 1 1
							setPri: 1
						)
					)
				)
			)
			(49
				(ego
					view: 0
					observeControl: 8192 4096
					setStep: 3 2
					setPri: -1
				)
				(if (< (ego y?) 126)
					(ego posn: 314 102)
				else
					(ego posn: 318 (ego y?))
				)
			)
			(else 
				(ego view: 0 posn: 160 160)
			)
		)
		(self setRegions: DUNE)
		(switch terminatorState
			(1
				(if (== (Random 1 2) 2)
					((= terminator (Actor new:)) posn: 1000 1000 init:)
					(= notifyCountdown (Random 2 10))
				)
			)
			(2
				((= terminator (Actor new:)) posn: 1000 1000 init:)
				(= notifyCountdown 3)
			)
		)
		(if (cast contains: terminator)
			(terminator
				view: 106
				setCycle: Walk
				observeControl: 2 8192 4096
				posn: 1000 1000
				init:
			)
			(= gGEgoX_4 166)
			(= gGEgoY_3 105)
			(switch prevRoomNum
				(east
					(terminator posn: 359 152)
				)
				(west
					(terminator posn: -40 158)
				)
				(south
					(terminator posn: 218 249)
				)
				(north
					(terminator posn: 166 105)
				)
			)
			(= terminatorState terminatorMEET)
			((ScriptID 501 0) notify: 1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if
			(and
				(== global104 0)
				(ego inRect: 200 117 237 134)
				(== (curRoom script?) 0)
			)
			(self setScript: egoDeath)
		)
		(if
			(and
				(cast contains: terminator)
				(terminator inRect: 200 117 237 134)
				(== (curRoom script?) 0)
			)
			(self setScript: termDeath)
		)
		(if (IsObject (ego mover?))
			(= theTheGGEgoX_5 ((ego mover?) x?))
			(= local7 ((ego mover?) y?))
			(= local8 1)
		else
			(= local8 0)
		)
		(cond 
			((& (= local0 (ego onControl: 0)) $0008) (curRoom newRoom: 48))
			((and (== global116 2) (not (& local0 $0002)))
				(= global116 0)
				(if (proc501_2 1)
					(ego posn: (ego x?) (+ (ego y?) 5))
				)
			)
			((and (== global116 4) (not (& local0 $0004))) (= global116 0) (ego ignoreControl: 4))
			(
				(and
					(& local0 $0002)
					(== global104 0)
					(not isHandsOff)
					(not global116)
				)
				(= global116 2)
				(= global104 1)
				(= gGEgoX_4 (ego x?))
				(= gGEgoY_3 (ego y?))
				(if local8
					(if gGEgoY_4
						(= egoY 400)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local7) (ego y?)))
						(= egoX theTheGGEgoX_5)
					)
				else
					(= egoY (ego y?))
					(= egoX (ego x?))
				)
				(ego
					view: 82
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
					setPri: 4
					ignoreControl: 8192
				)
			)
			(
				(and
					(& local0 $0002)
					(== global104 1)
					(not isHandsOff)
					(not global116)
				)
				(= global104 0)
				(= global116 2)
				(if local8
					(if gGEgoY_4
						(= egoY gGEgoY_4)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local7) (ego y?)))
						(= egoX theTheGGEgoX_5)
					)
				else
					(= egoY (ego y?))
					(= egoX (ego x?))
				)
				(ego
					view: 0
					xStep: 3
					yStep: 2
					looper: 0
					setMotion: MoveTo egoX egoY
					setPri: -1
					observeControl: 8192
					moveSpeed: 0
				)
			)
			(
				(and
					(== global104 1)
					(& local0 $0004)
					(not isHandsOff)
					(not global116)
				)
				(= global116 4)
				(= global104 2)
				(if local8
					(if gGEgoY_4
						(= egoY gGEgoY_4)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local7) (ego y?)))
						(= egoX theTheGGEgoX_5)
					)
				else
					(= egoY (ego y?))
					(= egoX (ego x?))
				)
				(ego
					view: 68
					posn: (ego x?) (- (ego y?) 3)
					observeControl: 4
				)
				(RedrawCast)
				(ego
					looper: 0
					setStep: 1 1
					setPri: 4
					ignoreControl: 4096
					setMotion: MoveTo egoX egoY
				)
			)
			(
				(and
					(== global104 2)
					(& local0 $0004)
					(not isHandsOff)
					(not global116)
				)
				(= global116 4)
				(= global104 1)
				(if local8
					(if gGEgoY_4
						(= egoY -400)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local7) (ego y?)))
						(= egoX theTheGGEgoX_5)
					)
				else
					(= egoY (ego y?))
					(= egoX (ego x?))
				)
				(ego
					view: 82
					posn: (ego x?) (- (ego y?) 3)
					observeControl: 4
				)
				(RedrawCast)
				(ego
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
					setPri: 4
					observeControl: 4096
					ignoreControl: 8192
				)
			)
			(
				(and
					(or (== global104 0) (== global104 1))
					(== global116 0)
					(or (proc501_2 global109) (< (ego y?) 92))
				)
				(= global104 1)
				(= egoY 180)
				(= egoX (ego x?))
				(ego setPri: 2 posn: (ego x?) (+ (ego y?) 8))
				(RedrawCast)
				(ego
					posn: (ego x?) (+ (ego y?) 8)
					view: 82
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
					ignoreControl: 8192
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 5])
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(saidEvent
					(cond 
						((Said 'look,explore/android,debris,heap')
							(cond 
								((cast contains: terminatorRemains)
									(Print
										(Format @invStr 52 0
											(if
												(and
													(InRoom iInvisibilityBelt 52)
													(< (ego distanceTo: terminatorRemains) 60)
													(== global104 0)
												)
												{Looking closely, you notice that the terminator's invisibility belt has survived relatively intact.}
											else
												{}
											)
										)
									)
								)
								((cast contains: terminator) (Print 52 1))
								(else (Print 52 2))
							)
						)
						((Said 'look>')
							(cond 
								(
									(or
										(Said '/area')
										(Said '/around')
										(Said '[<around][/!*]')
									)
									(Print 52 3)
								)
								((Said '/chute') (Print 52 4))
								((Said '/rock') (Print 52 5))
								((Said '/tongue') (Print 52 6))
							)
						)
						((Said 'converse/chute') (Print 52 7))
						((Said 'beat,attack/chute') (Print 52 8))
						((Said 'get/chute') (Print 52 9))
						((Said 'climb/') (Print 52 10))
						(
							(or
								(Said 'get/belt/orat,stick')
								(Said 'use/stick,orat')
								(Said 'get<use<(stick,orat)/belt')
							)
							(cond 
								((ego has: iInvisibilityBelt) (Print 52 11))
								((not (InRoom iInvisibilityBelt 52)) (Print 52 2))
								((not (ego has: iOratOnAStick)) (Print 52 12))
								((!= terminatorState terminatorDEAD) (Print 52 13))
								(
									(or
										(not (ego inRect: 160 107 198 147))
										(!= global104 0)
									)
									(Print 52 14)
								)
								(else (curRoom setScript: (ScriptID 520 0)))
							)
						)
						((or (Said 'get/belt/pole') (Said 'use/pole'))
							(cond 
								((ego has: iMetalPole) (Print 52 11))
								((not (InRoom iInvisibilityBelt 52)) (Print 52 2))
								((not (ego has: iMetalPole)) (Print 52 12))
								((!= terminatorState terminatorDEAD) (Print 52 13))
								(
									(and
										(not (ego inRect: 200 117 237 134))
										(!= global104 0)
									)
									(Print 52 14)
								)
								(else (curRoom setScript: (ScriptID 521 0)))
							)
						)
						((Said 'get/belt')
							(if (InRoom iInvisibilityBelt 52)
								(Print 52 15)
							else
								(Print 52 2)
							)
						)
						((Said '/ladder') (DontHave))
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not isHandsOff)
			(NormalEgo)
			(if
				(and
					(cast contains: terminator)
					(< (ego distanceTo: terminator) 120)
				)
				(= terminatorState 2)
			)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance egoDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local3 0)
				(HandsOff)
				(if (cast contains: terminator)
					((ScriptID 501) notify: 2)
				)
				(= global116 9999)
				(slurp number: 82 priority: 3 loop: 1 play:)
				(mouth
					view: 78
					setLoop: 1
					cel: 255
					x: (ego x?)
					setCycle: EndLoop self
				)
			)
			(1
				(tongue view: 78 setLoop: 2 x: (ego x?) show:)
				(ego
					view: 78
					setLoop: 3
					setCycle: Forward
					setPri: 8
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo (ego x?) 109 self
				)
			)
			(2
				(tongue setCel: 1)
				(ego setMotion: MoveTo (ego x?) 92 self)
			)
			(3
				(tongue view: 777 loop: 0)
				(ego setMotion: MoveTo (ego x?) 78 self)
				((= blood (Actor new:)) view: 777 loop: 0 init:)
			)
			(4
				(ego view: 777 loop: 0)
				(mouth setLoop: 4 setCycle: Forward)
				(blood
					view: 78
					setLoop: 5
					ignoreActors:
					illegalBits: 0
					posn: (ego x?) 67
					setPri: 8
					setStep: 1 4
					setMotion: MoveTo (ego x?) 129 self
				)
				(= local3 (+ local3 1))
			)
			(5
				(if (<= local3 6)
					(= state 3)
					(= cycles 2)
				else
					(blood dispose:)
					(= cycles 1)
				)
			)
			(6 (EgoDead 901 1 0 999))
		)
	)
)

(instance termDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(terminator ignoreActors: setMotion: 0)
				(mouth
					view: 78
					setLoop: 1
					cel: 255
					x: (terminator x?)
					setCycle: EndLoop self
				)
			)
			(1
				(tongue view: 78 setLoop: 2 cel: 0 x: (terminator x?))
				(= terminatorRemains (Actor new:))
				(terminatorRemains
					view: 78
					posn: (terminator x?) (terminator y?)
					setLoop: 6
					setCycle: Forward
					setPri: 8
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo (terminator x?) 109 self
					init:
				)
				((ScriptID 501) notify: 2)
			)
			(2
				(tongue setCel: 1)
				(terminatorRemains
					setMotion: MoveTo (terminatorRemains x?) 92 self
				)
			)
			(3
				(tongue view: 777 loop: 0)
				(terminatorRemains
					setMotion: MoveTo (terminatorRemains x?) 78 self
				)
			)
			(4
				(terminatorRemains view: 777 loop: 0)
				(mouth setLoop: 7 setCel: 0)
				(= seconds 3)
			)
			(5
				(mouth setCel: 1)
				(= cycles 2)
			)
			(6
				(cond 
					((>= (terminatorRemains x?) 230) (= egoX 230))
					((<= (terminatorRemains x?) 208) (= egoX 208))
					(else (= egoX (terminatorRemains x?)))
				)
				(terminatorRemains
					view: 78
					posn: (terminatorRemains x?) 72
					setLoop: 8
					setCel: 0
					setStep: 1 6
					setMotion: MoveTo egoX 125 self
				)
			)
			(7
				(mouth view: 777 loop: 0)
				(theGame changeScore: 45)
				(terminatorRemains
					setCel: -1
					setPri: 5
					setCycle: EndLoop self
				)
			)
			(8
				(HandsOn)
				(= theBelt (View new:))
				(theBelt
					view: 78
					loop: 9
					cel: 1
					setPri: 6
					posn: (terminatorRemains x?) (+ (terminatorRemains y?) 3)
					init:
				)
				(= terminatorState terminatorDEAD)
				(= roomWithDeadTerminator 52)
				(PutInRoom iInvisibilityBelt 52)
				(curRoom setScript: 0)
			)
		)
	)
)
