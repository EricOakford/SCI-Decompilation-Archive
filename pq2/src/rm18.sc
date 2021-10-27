;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include sci.sh)
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
	newProp
	newView
	local2
	local3
	local4
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm18 of Room
	(properties
		picture 18
		style $0001
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(= gunFireState 3)
		(= gunNotNeeded 1)
		(User canInput: 1)
		(Load rsVIEW 1)
		(Load rsVIEW 79)
		((= newProp (Prop new:))
			view: 79
			posn: 126 98
			setLoop: 1
			cel: 0
			setPri: 8
			init:
			stopUpd:
		)
		((= newView (View new:))
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
		(self setLocales: 153)
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
			((!= (mod (ego view?) 2) 1) (ego view: (+ (ego view?) 1)))
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
				(if (Btst 40)
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
			(evSAID
				(cond 
					((Said 'display/mugshot,painting,(shot<mug)')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 3)
						else
							(localproc_000c 18 0)
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,chamber,building]') (localproc_000c 18 1))
							((Said '/sign,flyer,ad')
								(if (> (ego x?) 95)
									(switch (Random 1 3)
										(1 (localproc_000c 18 2))
										(2 (localproc_000c 18 3))
										(3 (localproc_000c 18 4))
									)
								else
									(localproc_000c 18 2)
								)
							)
							((or (Said '<up') (Said '/ceiling')) (localproc_000c 18 5))
							((or (Said '<down') (Said '/floor')) (localproc_000c 18 6))
							((Said '/counter,agency') (localproc_000c 18 7))
							((Said '/hat') (localproc_000c 18 8))
							((Said '/turtle') (localproc_000c 18 9))
							((Said '/pane')
								(if (== (ego loop?) 2)
									(localproc_000c 18 10)
								else
									(localproc_000c 18 11)
								)
							)
							((Said '/bench') (localproc_000c 18 12))
							((Said '/rope') (localproc_000c 18 13))
							((Said '/painting') (localproc_000c 18 14))
							((Said '/agent,dude,broad') (localproc_000c 18 15))
							((Said '/list,rental[<customer]')
								(if (ego inRect: 76 113 173 141)
									(agentScript changeState: 1)
								else
									(localproc_000c 18 16)
								)
							)
						)
					)
					((Said 'ask/auto') (localproc_000c 18 17))
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
							(localproc_000c 18 16)
						)
					)
					((Said 'chat/agent,dude,broad')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 0)
						else
							(localproc_000c 18 16)
						)
					)
					((Said 'read/sign,flyer,ad')
						(switch (Random 1 3)
							(1 (localproc_000c 18 2))
							(2 (localproc_000c 18 3))
							(3 (localproc_000c 18 4))
						)
					)
					((Said 'display/badge')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 2)
						else
							(localproc_000c 18 18)
						)
					)
					((Said 'get/list[<rental,customer]') (localproc_000c 18 19))
					((Said 'arrest/agent,dude,broad,broad') (localproc_000c 18 20))
					((Said 'arrest/agent,dude,broad,broad') (localproc_000c 18 21))
					((Said 'fire,kill,beat/agent,dude,broad,broad') (localproc_000c 18 22))
					((Said 'rent[/auto]') (localproc_000c 18 23))
					((Said 'get/hat') (localproc_000c 18 24))
					((Said 'thank[/ya,dude,broad,broad,agent]')
						(if (ego inRect: 76 113 173 141)
							(agentScript changeState: 4)
						else
							(localproc_000c 18 16)
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
					(0 (localproc_000c 18 25))
					(1 (localproc_000c 18 26))
					(2 (localproc_000c 18 27))
					(3 (localproc_000c 18 28))
				)
			)
			(1
				(= local4 1)
				(if local2
					(localproc_000c 18 29)
					(localproc_000c 18 30)
					(localproc_000c 18 31)
				else
					(localproc_000c 18 32)
				)
			)
			(2
				(= local2 1)
				(switch (Random 0 1)
					(0 (localproc_000c 18 33))
					(1
						(localproc_000c 18 34)
						(localproc_000c 18 35)
					)
				)
				(if local4
					(localproc_000c 18 30)
					(localproc_000c 18 31)
				)
			)
			(3
				(= local3 1)
				(cond 
					(local2
						(cond 
							((ego has: 12)
								(switch (Random 0 1)
									(0
										(localproc_000c 18 36 82 112)
									)
									(1
										(localproc_000c 18 37 82 112)
									)
								)
							)
							((ego has: 23)
								(switch (Random 0 1)
									(0
										(localproc_000c 18 36 82 123)
									)
									(1
										(localproc_000c 18 37 82 123)
									)
								)
							)
							(else (localproc_000c 18 38))
						)
					)
					((or (ego has: 12) (ego has: 23))
						(localproc_000c
							18
							39
							82
							(if (ego has: 12) 112 else 123)
						)
					)
					(else (localproc_000c 18 38))
				)
			)
			(4
				(if local2
					(localproc_000c 18 40)
					(if local3 (localproc_000c 18 41))
				else
					(localproc_000c 18 42)
				)
			)
		)
	)
)
