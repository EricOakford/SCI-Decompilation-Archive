;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include game.sh)
(use Main)
(use Intrface)
(use Dune)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room46 0
)

(local
	local0
	egoY
	theGGEgoX_5
)
(instance lightning of Prop
	(properties)
)

(instance thunder of Sound
	(properties)
)

(instance Room46 of Room
	(properties
		picture 46
	)
	
	(method (init)
		(= east 47)
		(= west 45)
		(= north 42)
		(= south 50)
		(Load VIEW 76)
		(= global591 (= global109 9))
		(Load SOUND 31)
		(Load SOUND 32)
		(Load VIEW 76)
		(super init:)
		(= global116 0)
		(NormalEgo)
		(ego view: 0 observeControl: 8192 init:)
		(lightning
			view: 69
			loop: 4
			cel: 0
			posn: 14 52
			ignoreActors:
			setScript: flash
			init:
		)
		(switch prevRoomNum
			(42
				(= global104 4)
				(ego
					posn: 120 60
					view: 68
					ignoreControl: 8192 2048
					setPri: 3
					setStep: 1 1
				)
			)
			(45
				(switch global104
					(0
						(ego posn: 1 (ego y?) setPri: -1 view: 0)
					)
					(1
						(= global104 3)
						(ego
							posn: 1 97
							setPri: 3
							ignoreControl: 8192
							looper: (ScriptID DUNE 1)
							view: 82
						)
					)
					(2
						(= global104 4)
						(ego
							posn: 1 75
							ignoreControl: 8192
							setStep: 1 1
							setPri: 3
							view: 68
						)
					)
				)
			)
			(47
				(switch global104
					(0
						(ego view: 0)
						(ego posn: 318 (ego y?))
					)
					(1
						(ego
							posn: 318 158
							view: 82
							looper: (ScriptID DUNE 1)
							ignoreControl: 8192
							setStep: 2 1
							setPri: 5
						)
					)
					(2
						(= global104 2)
						(ego view: 82 ignoreControl: 8192 2048 setPri: 5)
						(if (< (ego y?) 107)
							(ego posn: 318 110)
						else
							(ego posn: 318 (ego y?))
						)
					)
					(3
						(ego
							posn: 318 80
							view: 82
							looper: (ScriptID DUNE 1)
							ignoreControl: 8192
							observeControl: 2048
							setStep: 2 1
							setPri: 3
						)
					)
					(4
						(ego
							posn: 318 61
							view: 68
							ignoreControl: 8192
							setPri: 3
							setStep: 1 1
						)
					)
				)
			)
			(50
				(= global104 0)
				(NormalEgo)
				(ego posn: (ego x?) 188 ignoreControl: 2048 view: 0)
			)
			(else 
				(ego posn: 154 174 observeControl: 1024)
			)
		)
		(self setRegions: DUNE)
		(if (cast contains: terminator)
			(terminator
				view: 106
				setCycle: Walk
				observeControl: 2
				observeControl: 8192 16 16384
				posn: 1000 1000
				init:
			)
			(= gGEgoX_4 23)
			(= gGEgoY_3 90)
			(switch prevRoomNum
				(east
					(terminator posn: 359 175)
				)
				(west
					(terminator posn: -40 175)
				)
				(south
					(terminator posn: 160 249)
				)
				(north (terminator posn: 23 90))
			)
			(= terminatorState 1)
			((ScriptID DUNE 0) notify: 1)
		else
			((ScriptID DUNE 0) notify: 4)
		)
		(curRoom setScript: Actions)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(= local0 (ego onControl: 0))
		(if
			(and
				(!= (self script?) duneFall)
				(or
					(and
						(& local0 $0008)
						(or (== global104 1) (== global104 2))
					)
					(and (& local0 $4000) (== global104 0))
				)
			)
			(HandsOff)
			(self setScript: duneFall)
		)
		(if (!= (curRoom script?) duneFall)
			(cond 
				((& local0 $0100) (curRoom newRoom: 42))
				(
					(and
						(== global116 2)
						(not isHandsOff)
						(not (& local0 $0002))
					)
					(= global116 0)
					(if (proc501_2 16)
						(ego posn: (ego x?) (+ (ego y?) 5))
					)
				)
				(
					(and
						(== global116 4)
						(not isHandsOff)
						(not (& local0 $0004))
					)
					(= global116 0)
				)
				(
					(and
						(== global116 16)
						(not isHandsOff)
						(not (& local0 $0010))
					)
					(= global116 0)
					(if (proc501_2 8)
						(ego posn: (ego x?) (+ (ego y?) 5))
					)
				)
				(
					(and
						(== global116 32)
						(not isHandsOff)
						(not (& local0 $0020))
					)
					(= global116 0)
				)
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
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY 400)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 82
						xStep: 2
						yStep: 1
						looper: (ScriptID DUNE 1)
						observeControl: 2048
						setMotion: MoveTo theGGEgoX_5 egoY
						setPri: 5
					)
				)
				(
					(and
						(& local0 $0010)
						(== global104 0)
						(not isHandsOff)
						(not global116)
					)
					(= global104 3)
					(= global116 16)
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY 400)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 82
						xStep: 2
						yStep: 1
						ignoreControl: 8192
						observeControl: 2048
						looper: (ScriptID DUNE 1)
						setMotion: MoveTo theGGEgoX_5 egoY
						setPri: 3
					)
				)
				(
					(and
						(& local0 $0002)
						(== global104 1)
						(not isHandsOff)
						(not global116)
					)
					(= global116 2)
					(= global104 0)
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY gGEgoY_4)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 0
						xStep: 3
						yStep: 2
						looper: 0
						observeControl: 8192
						ignoreControl: 2048
						setMotion: MoveTo theGGEgoX_5 egoY
						setPri: -1
						moveSpeed: 0
					)
				)
				(
					(and
						(& local0 $0010)
						(== global104 3)
						(not isHandsOff)
						(not global116)
					)
					(= global116 16)
					(= global104 0)
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY gGEgoY_4)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 0
						xStep: 3
						yStep: 2
						looper: 0
						ignoreControl: 2048
						observeControl: 8192
						setMotion: MoveTo theGGEgoX_5 egoY
						setPri: -1
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
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY gGEgoY_4)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 82
						looper: 0
						setPri: 5
						posn: (ego x?) (- (ego y?) 3)
						observeControl: 8192 2048
						setStep: 2 1
						setMotion: MoveTo theGGEgoX_5 egoY
					)
				)
				(
					(and
						(== global104 3)
						(& local0 $0020)
						(not isHandsOff)
						(not global116)
					)
					(= global116 32)
					(= global104 4)
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY gGEgoY_4)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 68
						posn: (ego x?) (- (ego y?) 3)
						looper: 0
						setStep: 1 1
						setMotion: MoveTo theGGEgoX_5 egoY
					)
				)
				(
					(and
						(== global104 2)
						(& local0 $0004)
						(not isHandsOff)
						(not global116)
					)
					(= global104 1)
					(= global116 4)
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY -400)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 82
						xStep: 2
						yStep: 1
						posn: (ego x?) (- (ego y?) 3)
						looper: (ScriptID DUNE 1)
						ignoreControl: 8192
						observeControl: 2048
						setMotion: MoveTo theGGEgoX_5 egoY
						setPri: 5
					)
				)
				(
					(and
						(== global104 4)
						(& local0 $0020)
						(not isHandsOff)
						(not global116)
					)
					(= global116 32)
					(= global104 3)
					(if (IsObject (ego mover?))
						(if gGEgoY_4
							(= egoY -400)
							(= theGGEgoX_5 gGEgoX_5)
						else
							(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
							(= theGGEgoX_5 ((ego mover?) x?))
						)
					else
						(= egoY (ego y?))
						(= theGGEgoX_5 (ego x?))
					)
					(ego
						view: 82
						xStep: 2
						yStep: 1
						posn: (ego x?) (- (ego y?) 3)
						looper: (ScriptID DUNE 1)
						ignoreControl: 8192
						observeControl: 2048
						setMotion: MoveTo theGGEgoX_5 egoY
						setPri: 3
					)
				)
				(
					(and
						(or (== global104 0) (== global104 3))
						(== global116 0)
						(proc501_2 global109)
					)
					(= global104 3)
					(= egoY 180)
					(= theGGEgoX_5 (ego x?))
					(ego setPri: 3 posn: (ego x?) (+ (ego y?) 8))
					(RedrawCast)
					(ego
						view: 82
						looper: (ScriptID DUNE 1)
						xStep: 2
						yStep: 1
						setMotion: MoveTo theGGEgoX_5 egoY
					)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 5])
		(if (or (event claimed?) (== isHandsOff TRUE)) (return TRUE))
		(return
			(switch (event type?)
				(saidEvent
					(if (Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/!*]')
								)
								(Print 46 0)
							)
							((Said '/cavity,pit,dune,bank,partition') (Print 46 1))
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript 988)
		(if
			(and
				(cast contains: terminator)
				(< (ego distanceTo: terminator) 120)
			)
			(= terminatorState 2)
		)
		(if (not isHandsOff) (super newRoom: newRoomNumber))
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(else )
		)
	)
)

(instance duneFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 76
					looper: 0
					setStep: 5 5
					setLoop: 0
					setAvoider: Avoider
					setPri: 5
				)
				(cond 
					((< (ego x?) 210) (ego setMotion: MoveTo 204 170 self))
					((< (ego x?) 269) (ego setMotion: MoveTo 252 170 self))
					(else (ego setMotion: MoveTo 296 168 self))
				)
			)
			(1
				(HandsOn)
				(ego view: 82 setLoop: -1 setAvoider: 0 setStep: 2 1)
				(= global104 2)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance flash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 8 30)))
			(1
				(lightning cel: 255 setCycle: EndLoop self)
			)
			(2
				(lightning cel: 0)
				(= seconds (Random 2 5))
			)
			(3
				(thunder priority: 2 number: (Random 31 32) play: self)
			)
			(4 (= state -1))
		)
	)
)
