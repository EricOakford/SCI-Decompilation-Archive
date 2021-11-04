;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm18 0
)

(local
	propAgent
	blondeAgent
	badgeShown
	mugshotShown
	checkedList
)
(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(instance rm18 of Room
	(properties
		picture 18
		style VSHUTTER
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(= gunFireState gunPROHIBITED)
		(= gunNotNeeded 1)
		(User canInput: 1)
		(Load VIEW 1)
		(Load VIEW 79)
		((= propAgent (Prop new:))
			view: 79
			posn: 126 98
			setLoop: 1
			cel: 0
			setPri: 8
			init:
			stopUpd:
		)
		((= blondeAgent (View new:))
			view: 79
			posn: 101 103
			loop: 3
			cel: 1
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 79
			posn: 3 96
			loop: 0
			cel: 0
			setPri: 10
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 79
			posn: 286 87
			loop: 0
			cel: 1
			setPri: 5
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 79
			posn: 142 64
			loop: 0
			cel: 2
			setPri: 15
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 79
			posn: 68 128
			loop: 0
			cel: 3
			setPri: 10
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 79
			posn: 126 126
			loop: 0
			cel: 4
			init:
			ignoreActors:
			setPri: 9
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 79
			posn: 130 126
			loop: 0
			cel: 5
			init:
			ignoreActors:
			setPri: 8
			stopUpd:
			addToPic:
		)
		(self setLocales: regFieldKit)
		(self setScript: rm18Script)
	)
	
	(method (dispose)
		(agentScript dispose:)
		(super dispose:)
	)
)

(instance rm18Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(or
					(and (< (ego x?) 2) (> (ego y?) 125))
					(and (< (ego y?) 125) (< (ego x?) 100))
				)
				(= perspective 0)
				(curRoom newRoom: 16)
			)
			((<= (ego y?) 120)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(StatusLine enable:)
				(ego
					view: (if gunDrawn 7 else 1)
					xStep: 3
					yStep: 2
					posn: (if (< (ego y?) 135) 105 else 4) (ego y?)
					setMotion: MoveTo 350 (ego y?)
					init:
				)
				(if (Btst fKeithFollows)
					((= keith (Actor new:))
						view: 20
						xStep: 3
						yStep: 2
						posn: (- (ego x?) 26) (ego y?)
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Follow ego 24
						init:
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'display/mugshot,painting,(shot<mug)')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 3)
						else
							(LocPrint 18 0)
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,chamber,building]')
								(LocPrint 18 1)
							)
							((Said '/sign,flyer,ad')
								(if (> (ego x?) 95)
									(switch (Random 1 3)
										(1
											(LocPrint 18 2)
										)
										(2
											(LocPrint 18 3)
										)
										(3
											(LocPrint 18 4)
										)
									)
								else
									(LocPrint 18 2)
								)
							)
							(
								(or
									(Said '<up')
									(Said '/ceiling')
								)
								(LocPrint 18 5)
							)
							(
								(or
									(Said '<down')
									(Said '/floor')
								)
								(LocPrint 18 6)
							)
							((Said '/counter,agency')
								(LocPrint 18 7)
							)
							((Said '/hat')
								(LocPrint 18 8)
							)
							((Said '/turtle')
								(LocPrint 18 9)
							)
							((Said '/pane')
								(if (== (ego loop?) 2)
									(LocPrint 18 10)
								else
									(LocPrint 18 11)
								)
							)
							((Said '/bench')
								(LocPrint 18 12)
							)
							((Said '/rope')
								(LocPrint 18 13)
							)
							((Said '/painting')
								(LocPrint 18 14)
							)
							((Said '/agent,dude,broad')
								(LocPrint 18 15)
							)
							((Said '/list,rental[<customer]')
								(if (ego inRect: 76 113 173 141)
									(agentScript changeState: 1)
								else
									(LocPrint 18 16)
								)
							)
						)
					)
					((Said 'ask/auto')
						(LocPrint 18 17)
					)
					(
						(or
							(Said 'display,get,see,ask/list[<customer,rental,auto]')
							(Said '[display,get,see,ask]/list,agreement,rental')
							(Said 'display,get,see,ask/i/list,rental,agreement')
							(Said 'chat,ask/agent/customer,rental,list,auto')
						)
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 1)
						else
							(LocPrint 18 16)
						)
					)
					((Said 'chat/agent,dude,broad')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 0)
						else
							(LocPrint 18 16)
						)
					)
					((Said 'read/sign,flyer,ad')
						(switch (Random 1 3)
							(1
								(LocPrint 18 2)
							)
							(2
								(LocPrint 18 3)
							)
							(3
								(LocPrint 18 4)
							)
						)
					)
					((Said 'display/badge')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 2)
						else
							(LocPrint 18 18)
						)
					)
					((Said 'get/list[<rental,customer]')
						(LocPrint 18 19)
					)
					((Said 'arrest/agent,dude,broad,broad')
						(LocPrint 18 20)
					)
					((Said 'arrest/agent,dude,broad,broad')
						(LocPrint 18 21)
					)
					((Said 'fire,kill,beat/agent,dude,broad,broad')
						(LocPrint 18 22)
					)
					((Said 'rent[/auto]')
						(LocPrint 18 23)
					)
					((Said 'get/hat')
						(LocPrint 18 24)
					)
					((Said 'thank[/ya,dude,broad,broad,agent]')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 4)
						else
							(LocPrint 18 16)
						)
					)
				)
			)
		)
	)
)

(instance agentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 0 3)
					(0
						(LocPrint 18 25)
					)
					(1
						(LocPrint 18 26)
					)
					(2
						(LocPrint 18 27)
					)
					(3
						(LocPrint 18 28)
					)
				)
			)
			(1
				(= checkedList 1)
				(if badgeShown
					(LocPrint 18 29)
					(LocPrint 18 30)
					(LocPrint 18 31)
				else
					(LocPrint 18 32)
				)
			)
			(2
				(= badgeShown 1)
				(switch (Random 0 1)
					(0 (LocPrint 18 33))
					(1
						(LocPrint 18 34)
						(LocPrint 18 35)
					)
				)
				(if checkedList
					(LocPrint 18 30)
					(LocPrint 18 31)
				)
			)
			(3
				(= mugshotShown 1)
				(cond 
					(badgeShown
						(cond 
							((ego has: iNewMugShot)
								(switch (Random 0 1)
									(0
										(LocPrint 18 36 82 112)
									)
									(1
										(LocPrint 18 37 82 112)
									)
								)
							)
							((ego has: iOldMugShot)
								(switch (Random 0 1)
									(0
										(LocPrint 18 36 82 123)
									)
									(1
										(LocPrint 18 37 82 123)
									)
								)
							)
							(else
								(LocPrint 18 38)
							)
						)
					)
					(
						(or
							(ego has: iNewMugShot)
							(ego has: iOldMugShot)
						)
						(LocPrint 18 39 82
							(if (ego has: 12)
								112
							else
								123
							)
						)
					)
					(else
						(LocPrint 18 38)
					)
				)
			)
			(4
				(if badgeShown
					(LocPrint 18 40)
					(if mugshotShown
						(LocPrint 18 41)
					)
				else
					(LocPrint 18 42)
				)
			)
		)
	)
)
