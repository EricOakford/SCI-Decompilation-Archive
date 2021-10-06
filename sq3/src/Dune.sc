;;; Sierra Script 1.0 - (do not remove this comment)
(script# DUNE)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	regDune 0
	DUNELOOPER 1
	proc501_2 2
	proc501_3 3
)

(local
	local0
	terminatorX
	terminatorY
	local3
	twistSound
	local5
	scorpion
	[local7 12]
)
(procedure (proc501_2 param1 &tmp temp0)
	(return
		(if (& (= temp0 (OnControl 2 (ego x?) (ego y?))) param1)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (proc501_3 param1 &tmp i)
	(for ((= i 0)) param1 ((++ i))
		(>>= param1 $0001)
	)
	(return (- i 1))
)

(class AView of View
	(method (delete)
		(&= signal (~ viewAdded))
		(super delete:)
	)
)

(instance footPrint of AView)

(class dMoveTo of Motion
	(method (init theClient theX theY theCaller &tmp [temp0 2])
		(= client theClient)
		(= x theX)
		(if (!= (ego looper?) 0)
			(= y (+ (ego y?) (- (ego y?) theY)))
		else
			(= y theY)
		)
		(if (== argc 4)
			(= caller theCaller)
		)
		(= b-moveCnt 0)
		(theClient
			heading: (GetAngle (theClient x?) (theClient y?) x y)
		)
		(if (== global104 1)
			(cond 
				((or (< (ego heading?) 45) (> (ego heading?) 315))
					(ego loop: 2)
				)
				((and (>= (ego heading?) 45) (< (ego heading?) 135))
					(ego loop: 0)
				)
				((and (>= (ego heading?) 135) (< (ego heading?) 225))
					(ego loop: 3)
				)
				(else
					(ego loop: 1)
				)
			)
		else
			(DirLoop theClient (theClient heading?))
		)
		(InitBresen self)
	)
)

(instance regDune of Region
	(method (init)
		(Load VIEW 777)
		(Load VIEW 82)
		(Load VIEW 0)
		(Load VIEW 68)
		(Load VIEW 106)
		(Load VIEW 108)
		(User mapKeyToDir: FALSE)
		(super init:)
	)
	
	(method (dispose)
		(User mapKeyToDir: TRUE)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp eType egoX egoY temp3 temp4 eMsg)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (User controls?) TRUE)
				(= eType (event type?))
				(MapKeyToDir event)
				(switch (event type?)
					(mouseDown
						(if (and (curRoom controls?) (IsObject ego))
							(= gGEgoY_4 (event y?))
							(= gGEgoX_5 (event x?))
							(= gGEgoY_5 (ego y?))
							(ego setMotion: dMoveTo (event x?) (event y?))
							(User prevDir: 0)
							(event claimed: TRUE)
						)
					)
					(direction
						(if (and (curRoom controls?) (IsObject ego))
							(= eMsg (event message?))
							(= gGEgoX_5 0)
							(= gGEgoY_4 0)
							(if
								(and
									(== eType keyDown)
									(== gPEventMessage eMsg)
									(IsObject (ego mover?))
								)
								(= eMsg 0)
							)
							(= gPEventMessage eMsg)
							(if (== eMsg 0)
								(ego setMotion: 0)
								(return (event claimed: TRUE))
							)
							(if (ego mover?)
								(if
									(== eMsg
										(cond 
											((or (== global104 0) (== global104 4) (== global104 2))
												(cond 
													((== (ego heading?) 0) 1)
													((< (ego heading?) 90) 2)
													((== (ego heading?) 90) 3)
													((< (ego heading?) 180) 4)
													((== (ego heading?) 180) 5)
													((< (ego heading?) 270) 6)
													((== (ego heading?) 270) 7)
													(else 8)
												)
											)
											((== (ego heading?) 180) 1)
											((< (ego heading?) 90) 4)
											((== (ego heading?) 90) 3)
											((< (ego heading?) 180) 2)
											((== (ego heading?) 0) 5)
											((< (ego heading?) 270) 8)
											((== (ego heading?) 270) 7)
											(else 6)
										)
									)
									(return (event claimed: TRUE))
								)
							)
							(= egoX (ego x?))
							(= egoY (ego y?))
							(= temp3 (* (ego xStep?) 400))
							(= temp4 (* (ego yStep?) 400))
							(if
								(and
									(!= global104 0)
									(!= global104 4)
									(!= global104 2)
								)
								(*= temp4 -1)
							)
							(switch eMsg
								(1
									(-= egoY temp4)
								)
								(2
									(+= egoX temp3)
									(-= egoY temp4)
								)
								(3
									(+= egoX temp3)
								)
								(4
									(+= egoX temp3)
									(+= egoY temp4)
								)
								(5
									(+= egoY temp4)
								)
								(6
									(-= egoX temp3)
									(+= egoY temp4)
								)
								(7
									(-= egoX temp3)
								)
								(8
									(-= egoX temp3)
									(-= egoY temp4)
								)
							)
							(ego setMotion: MoveTo egoX egoY)
							(event claimed: TRUE)
						)
					)
					(saidEvent
						(cond 
							((Said 'look>')
								(cond 
									((Said '/desert')
										(Print 501 0)
									)
									((Said '/dirt,dirt')
										(Print 501 1)
									)
									((Said '/dune')
										(Print 501 2)
									)
									((or (Said '/dirt') (Said '<down'))
										(Print 501 3)
									)
									((Said '/lightning')
										(Print 501 4)
									)
									((or (Said '/cloud,air') (Said '<up'))
										(Print 501 5)
									)
									((Said '/down')
										(Print 501 6)
									)
									((Said '/bug')
										(if (cast contains: scorpion)
											(Print 501 7)
										else
											(Print 501 8)
										)
									)
									((Said '/butte,toe')
										(Print 501 9)
									)
									((Said '/footprint')
										(if (cast contains: terminator)
											(Print 501 10)
										else
											(Print 501 11)
										)
									)
									((Said '/rock')
										(Print 501 12)
									)
									((Said '/cloud')
										(Print 501 13)
									)
									((Said '/butte')
										(Print 501 14)
									)
								)
							)
							((Said 'get>')
								(cond 
									((Said '/dirt')
										(Print 501 15)
									)
									((Said '/rock')
										(Print 501 16)
									)
									((Said '/bug')
										(if (cast contains: scorpion)
											(Print 501 17)
										else
											(Print 501 8)
										)
									)
								)
							)
							((Said 'conceal')
								(Print 501 18)
							)
							((Said 'dig')
								(Print 501 19)
							)
							((or (Said 'attack/bug') (Said 'stair/bug'))
								(if (cast contains: scorpion)
									(Print 501 20)
								)
							)
							((Said 'climb,sit,crawl,lie')
								(Print 501 21)
							)
							((Said 'converse/android')
								(if (cast contains: terminator)
									(Print 501 22)
								else
									(Print 501 23)
								)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(switch param1
			(1
				(terminator view: 777 setCycle: Walk setScript: tActions)
				(= local0 0)
			)
			(2
				(terminator dispose:)
			)
			(3
				(terminator
					view: 777
					setMotion: 0
					setScript: 0
					ignoreActors:
				)
			)
			(4
				(if (== (Random 1 4) 3)
					((= scorpion (ScriptID 511 0)) init:)
				)
			)
		)
	)
)

(instance DUNELOOPER of Script
	(method (doit)
		(if (or (== global104 1) (== global104 3))
			(ego
				loop:
					(cond 
						((or (< (ego heading?) 45) (> (ego heading?) 315)) loopS)
						((and (>= (ego heading?) 45) (< (ego heading?) 135)) loopE)
						((and (>= (ego heading?) 135) (< (ego heading?) 225)) loopN)
						(else loopW)
					)
			)
		else
			(DirLoop ego (ego heading?))
		)
	)
)

(instance tActions of Script
	;this has been newly decompiled
	(method (doit)
		(if (!= curRoomNum newRoomNum) (return))
		(if (and (!= global104 0) (== local0 1))
			(= local0 (= state 7))
			(terminator
				setMotion: MoveTo gGEgoX_4 gGEgoY_3 self
				ignoreControl: 2
			)
		)
		(if
		(and (== local0 7) (& (terminator onControl:) $0002))
			(terminator view: 777 setMotion: 0)
			(self changeState: 8)
			(= local0 8)
		)
		(if
			(and
				(or (== local0 8) (== local0 7))
				(== global104 0)
			)
			(terminator
				view: 106
				observeControl: 2
				setAvoider: Avoider
				setMotion: Chase ego 10 self
			)
			(= seconds 0)
			(= local0 (= state 1))
		)
		(if
			(and
				(!= local0 10)
				(&
					(OnControl
						2
						(- (terminator x?) 9)
						(- (terminator y?) 3)
						(+ (terminator x?) 9)
						(terminator y?)
					)
					global591
				)
			)
			(if (== local0 1)
				(terminator posn: terminatorX (+ terminatorY 3))
				(= seconds 0)
				(= state 1)
				(terminator
					setAvoider: Avoider
					setMotion: Chase ego 10 self
				)
			else
				(terminator setMotion: 0 ignoreActors: view: 777)
			)
		)
		(if
		(!= (OnControl 1 (terminator x?) (terminator y?)) 0)
			(= terminatorX (terminator x?))
			(= terminatorY (terminator y?))
		)
		(if (or (== local0 1) (== local0 7))
			(if (== (terminator cel?) 0)
			else
				(if (< (terminator loop?) 2) (== (terminator cel?) 4))
				(== (terminator cel?) 3)
			)
			(= local3
				(OnControl 2 (terminator x?) (terminator y?))
			)
			(if
				(and
					(!= (= local3 (proc501_3 local3)) -1)
					(!= (terminator view?) 777)
				)
				(footPrint
					view: 106
					loop: (terminator loop?)
					cel: (terminator cel?)
					ignoreActors: 1
					posn: (terminator x?) (terminator y?)
					addToPic:
				)
			)
		)
		(if (!= curRoomNum newRoomNum) (return))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds notifyCountdown)
			)
			(1
				(if (== global104 0)
					(terminator
						view: 106
						setMotion: Chase ego 10 self
						setAvoider: Avoider
						ignoreActors:
					)
					(= local0 1)
				else
					(= local0 (= state 7))
					(terminator
						view: 106
						setMotion: MoveTo gGEgoX_4 gGEgoY_3 self
						ignoreControl: 2
					)
				)
			)
			(2
				(if (!= curRoomNum newRoomNum) (return))
				(= local0 10)
				(= global116 1)
				(HandsOff)
				(terminator
					ignoreActors:
					illegalBits: 0
					posn: (ego x?) (ego y?)
				)
				(ego
					view: 106
					setLoop: 4
					cel: 255
					ignoreHorizon:
					illegalBits: 0
					setCycle: EndLoop
					setPri: (ego priority?)
					setStep: 4 4
					setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 15) self
				)
				(RedrawCast)
			)
			(3
				(ego setLoop: 5 setCycle: Forward)
				(terminator
					view: 106
					setLoop: 6
					illegalBits: 0
					setPri: (ego priority?)
					setCycle: Forward
					show:
				)
				(= seconds 2)
			)
			(4
				(terminator
					view: 106
					setLoop: 7
					setPri: (ego priority?)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(5
				(ego hide:)
				((= twistSound (Sound new:))
					number: 97
					loop: -1
					priority: 99
					play:
				)
				(terminator setLoop: 8 setCycle: Forward)
				(= seconds 6)
			)
			(6
				(twistSound stop:)
				(terminator setLoop: 8 setCel: 0)
				(EgoDead 901 0 14 16)
			)
			(8
				(= seconds 12)
				(= local0 8)
			)
			(9
				(if (and (!= global104 1) (!= global104 0))
					(= seconds (= state 8))
				else
					(self changeState: 2)
				)
			)
		)
	)
)
