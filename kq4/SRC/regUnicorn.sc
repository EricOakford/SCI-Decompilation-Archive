;;; Sierra Script 1.0 - (do not remove this comment)
(script# UNICORN)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	regUnicorn 0
)
(synonyms
	(kiss kiss embrace)
	(unicorn horse)
)

(local
	heart
	local1
)
(instance uniTheme of Sound
	(properties
		number 32
	)
)

(instance regUnicorn of Region
	(method (init)
		(super init:)
		(if (== 0 unicornRoom)
			(switch (Random 1 3)
				(1 (= unicornRoom 20))
				(2 (= unicornRoom 26))
				(3 (= unicornRoom 27))
			)
		)
		(if (== curRoomNum unicornRoom)
			(Load VIEW 388)
			(if (ego has: iGoldenBridle)
				(Load VIEW 389)
				(Load VIEW 381)
			)
			(if (or (== unicornState uniINITIAL) (== unicornState uniFINAL))
				(Load VIEW 382)
				(Load VIEW 383)
				(Load VIEW 380)
			)
			(if (ego has: iCupidBow)
				(Load VIEW 31)
				(Load VIEW 681)
			)
			(= unicorn (Actor new:))
			(unicorn
				view: 388
				xStep: 4
				yStep: 1
				setCycle: Forward
				cycleSpeed: 2
				setAvoider: Avoider 1
				init:
			)
			(switch curRoomNum
				(20
					(unicorn x: 145 y: 110)
				)
				(26
					(unicorn loop: 0 posn: 226 97)
				)
				(27
					(unicorn
						x: 177
						y: 139
						loop: (if (< (ego x?) (unicorn x?)) 0 else 1)
					)
				)
			)
			(if (< (ego x?) (unicorn x?))
				(unicorn loop: 0)
			else
				(unicorn loop: 1)
			)
			(unicorn setScript: uniActions)
		)
	)
	
	(method (dispose)
		(timers eachElementDo: #dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp index)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(!= (uniActions state?) 3)
					(cast contains: unicorn)
					(!= (unicorn script?) 0)
				)
				(cond 
					((and (== unicornState uniFINAL) (Said '/unicorn'))
						(Print 518 0)
					)
					((Said 'launch/arrow,unicorn,arrow')
						(if (and (ego has: iCupidBow) (< ((Inventory at: iCupidBow) loop?) 2))
							(if (!= unicornState uniINITIAL)
								(return (Print 518 1))
							)
							(cond 
								((!= (ego view?) 2)
									(Print 518 2)
								)
								((unicorn inRect: 0 0 319 189)
									(Face ego unicorn)
									(uniActions changeState: 10)
									(if (not shotUnicorn)
										(= shotUnicorn TRUE)
										(theGame changeScore: 4)
									)
									((Inventory at: iCupidBow) loop: (+ ((Inventory at: iCupidBow) loop?) 1))
								)
								(else
									(Print 518 3)
								)
							)
						else
							(Print 518 4)
						)
					)
					((or (Said 'converse[/noword]') (Said 'converse/unicorn'))
						(if (cast contains: unicorn)
							(switch unicornState
								(uniINITIAL
									(Print 518 5)
								)
								(uniSHOT
									(Print 518 6)
								)
								(uniBRIDLED
									(Print 518 7)
								)
							)
						else
							(Print 518 8)
						)
					)
					((Said 'look/horn')
						(if (cast contains: unicorn)
							(Print 518 9)
						else
							(event claimed: FALSE)
						)
					)
					((or (Said 'bit/unicorn') (Said 'place/bit'))
						(switch unicornState
							(uniINITIAL
								(Print 518 10)
							)
							(uniSHOT
								(if (ego has: iGoldenBridle)
									(if
										(or
											(and
												(== (unicorn loop?) 0)
												(ego
													inRect:
														(+ (unicorn x?) 20)
														(- (unicorn y?) 5)
														(+ (unicorn x?) 40)
														(+ (unicorn y?) 5)
												)
											)
											(ego
												inRect:
													(- (unicorn x?) 40)
													(- (unicorn y?) 5)
													(- (unicorn x?) 20)
													(+ (unicorn y?) 5)
											)
										)
										(Print 518 11)
										(theGame changeScore: 3)
										((Inventory at: iGoldenBridle) moveTo: 205)
										(uniActions changeState: 20)
									else
										(Print 800 1)
									)
								else
									(Print 518 12)
								)
							)
							(uniBRIDLED
								(Print 518 13)
							)
						)
					)
					((or (Said 'kiss[/noword]') (Said 'kiss/unicorn'))
						(if (cast contains: unicorn)
							(cond 
								((== unicornState uniINITIAL)
									(Print 518 14)
								)
								(
									(or
										(and
											(== (unicorn loop?) 0)
											(ego
												inRect:
													(+ (unicorn x?) 20)
													(- (unicorn y?) 5)
													(+ (unicorn x?) 40)
													(+ (unicorn y?) 5)
											)
										)
										(ego
											inRect:
												(- (unicorn x?) 40)
												(- (unicorn y?) 5)
												(- (unicorn x?) 20)
												(+ (unicorn y?) 5)
										)
									)
									(Print 518 15)
								)
								(else
									(Print 800 1)
								)
							)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/unicorn>')
						(if (cast contains: unicorn)
							(cond 
								((Said 'look')
									(switch unicornState
										(uniINITIAL
											(Print 518 16)
										)
										(uniSHOT
											(Print 518 17)
										)
										(uniBRIDLED
											(Print 518 18)
										)
									)
								)
								((or (Said 'mount') (Said 'get<on') (Said 'climb<on'))
									(switch unicornState
										(uniINITIAL
											(Print 518 19)
										)
										(uniSHOT
											(Print 518 20)
										)
										(uniBRIDLED
											(if (< (ego distanceTo: unicorn) 10)
												(if (> (ego y?) (unicorn y?))
													(ego
														view: 389
														setAvoider: (Avoider new:)
														ignoreActors:
														setMotion: 0
														posn: (unicorn x?) (unicorn y?)
														cel: 0 71
													)
													(if (== (unicorn loop?) 0)
														(ego loop: 0)
													else
														(ego loop: 1)
													)
													(= unicornState uniBRIDLED)
													(= unicornRoom 333)
													(= newRoomNum 333)
												else
													(Print 518 21)
												)
											else
												(Print 800 1)
											)
										)
									)
								)
								((Said 'get,capture')
									(switch unicornState
										(uniINITIAL
											(Print 518 19)
										)
										(uniSHOT
											(Print 518 22)
										)
										(uniBRIDLED
											(Print 518 23)
										)
									)
								)
								((Said 'pat')
									(cond 
										((== unicornState uniINITIAL)
											(Print 518 19)
										)
										(
											(or
												(and
													(== (unicorn loop?) 0)
													(ego
														inRect:
															(+ (unicorn x?) 20)
															(- (unicorn y?) 5)
															(+ (unicorn x?) 40)
															(+ (unicorn y?) 5)
													)
												)
												(ego
													inRect:
														(- (unicorn x?) 40)
														(- (unicorn y?) 5)
														(- (unicorn x?) 20)
														(+ (unicorn y?) 5)
												)
											)
											(Print 518 24)
										)
										(else
											(Print 800 1)
										)
									)
								)
								((Said 'feed')
									(Print 518 25)
								)
								((Said 'guide')
									(switch unicornState
										(uniINITIAL
											(Print 518 14)
										)
										(uniSHOT
											(Print 518 26)
										)
										(uniBRIDLED
											(Print 518 23)
										)
									)
								)
							)
						else
							(Print 518 27)
						)
					)
					((Said 'deliver>')
						(if (= index (inventory saidMe:))
							(if (ego has: (inventory indexOf: index))
								(Print 518 28)
							else
								(Print 800 2)
							)
						else
							(Print 518 29)
							(event claimed: TRUE)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance uniActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(uniTheme play:)
				(= seconds 4)
			)
			(1
				(switch unicornState
					(uniINITIAL
						(unicorn
							view: 382
							cel: 0
							cycleSpeed: 1
							setCycle: EndLoop self
						)
					)
					(uniSHOT
						(self changeState: 13)
					)
					(uniBRIDLED
						(unicorn view: 381 cycleSpeed: 1 setCycle: Forward)
					)
					(uniFINAL
						(unicorn
							view: 382
							cel: 0
							cycleSpeed: 1
							setCycle: EndLoop self
						)
					)
				)
			)
			(2
				(switch curRoomNum
					(20
						(unicorn view: 383 cycleSpeed: 0 setCycle: Walk)
						(if (< (ego x?) (unicorn x?))
							(unicorn setMotion: MoveTo 350 95 self)
						else
							(unicorn setMotion: MoveTo -50 91 self)
						)
					)
					(26
						(unicorn view: 383 cycleSpeed: 0 setCycle: Walk)
						(unicorn setMotion: MoveTo 350 94 self)
					)
					(27
						(unicorn view: 383 cycleSpeed: 0 setCycle: Walk)
						(if (< (ego x?) (unicorn x?))
							(unicorn setMotion: MoveTo 350 139 self)
						else
							(unicorn setMotion: MoveTo -50 139 self)
						)
					)
				)
			)
			(3
				(uniTheme dispose:)
				(unicorn dispose:)
				(switch curRoomNum
					(20
						(= unicornRoom 27)
					)
					(26
						(= unicornRoom 27)
					)
					(27
						(if (< (unicorn x?) 0)
							(= unicornRoom 26)
						else
							(= unicornRoom 20)
						)
					)
				)
			)
			(10
				(HandsOff)
				(Face ego unicorn)
				(ego view: 31 cel: 0 setCycle: EndLoop self)
			)
			(11
				(ego setCycle: BegLoop)
				(= unicornState uniSHOT)
				(= heart (Prop new:))
				(unicorn setMotion: 0 setCycle: 0)
				(heart
					view: 681
					cel: 0
					loop: 0
					setPri: 15
					posn: (unicorn x?) (- (unicorn y?) 15)
					setCycle: EndLoop self
					init:
				)
			)
			(12
				(heart dispose:)
				(Print 518 30 #at -1 10)
				(unicorn view: 383 cycleSpeed: 0 setCycle: Walk)
				(ego view: 2 setCycle: Walk)
				(HandsOn)
				(Print 518 31 #at -1 10)
				(switch curRoomNum
					(20
						(unicorn setMotion: MoveTo 160 110 self)
					)
					(21
						(unicorn setMotion: MoveTo 142 91 self)
					)
					(26
						(unicorn setMotion: MoveTo 226 99 self)
					)
					(27
						(unicorn setMotion: MoveTo 177 139 self)
					)
				)
			)
			(13
				(switch (++ local1)
					(1
						(unicorn view: 380 cycleSpeed: 2 setCycle: Forward)
						((ScriptID 0 4) setReal: self 3)
					)
					(2
						(unicorn view: 388 cycleSpeed: 2 setCycle: Forward)
						((ScriptID 0 4) setReal: self 5)
					)
					(3
						(unicorn view: 388 cel: 0 setCycle: 0)
						((ScriptID 0 4) setReal: self 7)
					)
				)
			)
			(14
				(if (== local1 3)
					(= local1 0)
				)
				(self changeState: 13)
			)
			(20
				(unicorn view: 381 setCycle: Forward)
				((Inventory at: iGoldenBridle) moveTo: 205)
				(= unicornState uniBRIDLED)
			)
		)
	)
)
