;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm81 0
)

(local
	[local0 3]
	local3
	[local4 4]
	duck
	lookedDown
	manhole
	manholeCloseup
	dogIsHere
	dog
	dogIsGone
	dogTimer
	foundManhole
	climbingDown
	local18
	muggerTimer
	local20
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm81 of Room
	(properties
		picture 81
		style $0000
	)
	
	(method (init)
		(super init:)
		(= dogTimer (Random 100 400))
		(if (and (not muggerFleeing) (not global236))
			(= muggerTimer (Random 30 50))
		)
		(HandsOn)
		(Load rsVIEW 0)
		(Load rsVIEW 4)
		(Load rsVIEW 6)
		(Load rsVIEW 261)
		(Load rsVIEW 191)
		(if (not muggerFleeing)
			(Load rsVIEW 88)
			(Load rsVIEW 32)
			(Load rsVIEW 46)
			(Load rsVIEW 52)
			(Load rsVIEW 264)
		)
		(= gunNotNeeded 0)
		(= gunFireState 1)
		(= local20 100)
		((= manhole (View new:))
			view: 261
			loop: 2
			cel: (if manholeIsOpen 1 else 0)
			posn: 62 189
			init:
			ignoreActors:
			stopUpd:
		)
		((= duck (Actor new:))
			view: 264
			posn: -15 124
			init:
			illegalBits: 0
			setStep: 2 1
			moveSpeed: 1
			setCycle: Forward
			setScript: duckScript
		)
		(if (== prevRoomNum 120)
			(entranceScript changeState: 0)
		else
			(ego
				posn: 1 171
				view: (if (not gunDrawn) 0 else 6)
				setPri: -1
				init:
				setMotion: MoveTo 320 171
				startUpd:
			)
			(if manholeIsOpen
				(= lookedDown 1)
				(ego illegalBits: -28672)
			)
		)
	)
	
	(method (doit &tmp [temp0 20])
		(if (and global236 (== (curRoom script?) 0))
			(self setScript: (ScriptID 77))
		)
		(if (and (not muggerFleeing) (> muggerTimer 1))
			(-- muggerTimer)
		)
		(if (> dogTimer 0) (-- dogTimer))
		(if
			(and
				(== muggerTimer 1)
				(== dogTimer 0)
				(not gunDrawn)
				(not isHandsOff)
				(ego inRect: 110 156 185 180)
			)
			(self setScript: (ScriptID 77))
			(= local3 1)
			(= muggerTimer 0)
		)
		(cond 
			((> local20 1) (-- local20))
			((== local20 1)
				(= local20 0)
				(duckScript changeState: 0)
				(= local20 (Random 200 300))
			)
		)
		(if (and (< (ego x?) 0) (not isHandsOff))
			(curRoom newRoom: 79)
		)
		(if manholeIsOpen
			(if
				(and
					(== (ego onControl: 1) 8192)
					(not local18)
					(not climbingDown)
				)
				(= local18 1)
				(localproc_000c 81 0)
			)
			(if (not (== (ego onControl: 1) 8192)) (= local18 0))
		)
		(if
		(and (cast contains: manholeCloseup) (> (ego x?) 135))
			(manholeCloseup dispose:)
		)
		(if
		(and muggerFleeing (not dogIsHere) (not dogIsGone))
			(doggieScript changeState: 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(exitScript dispose:)
		(entranceScript dispose:)
		(coverScript dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '[*]/dog')
						(cond 
							(dogIsHere (localproc_000c 81 1))
							(dogIsGone (localproc_000c 81 2))
							(else (localproc_000c 81 3))
						)
					)
					((Said 'look>')
						(cond 
							((Said '/chamber') (localproc_000c 81 4))
							((Said '[<at,around][/scenery,park]') (localproc_000c 81 5))
							((Said '/dude,mugger') (localproc_000c 81 6))
							((Said '/sand') (localproc_000c 81 7))
							((Said '/duck,bird') (localproc_000c 81 8))
							((Said '/egg') (localproc_000c 81 9))
							((Said '/tree') (localproc_000c 81 10))
							((Said '/bush,brush,bush') (localproc_000c 81 11))
							((or (Said '<up') (Said '/air')) (localproc_000c 81 12))
							((Said '/flower') (localproc_000c 81 13))
							((Said '/chrysanthemum') (localproc_000c 81 14))
							((Said '/pond,water') (localproc_000c 81 15))
							((Said '/cloud') (localproc_000c 81 16))
							((Said '/rock') (localproc_000c 81 17))
							((or (Said '/dirt') (Said '<down'))
								(if (ego inRect: 0 160 132 200)
									(if (not manholeIsOpen)
										(SolvePuzzle 1 100)
										(localproc_000c 81 18)
										(localproc_000c 81 19)
										(= lookedDown 1)
									else
										(localproc_000c 81 20)
										(= lookedDown 1)
									)
								else
									(localproc_000c 81 21)
								)
							)
							((Said '/sewer,complex')
								(if manholeIsOpen
									(localproc_000c 81 22)
								else
									(localproc_000c 81 23)
								)
							)
							((Said '/ladder')
								(if manholeIsOpen
									(localproc_000c 81 24)
								else
									(localproc_000c 81 25)
								)
							)
							((Said '/clue')
								(cond 
									((not foundManhole) (localproc_000c 81 26))
									((and foundManhole (ego inRect: 0 160 132 200)) (localproc_000c 81 27))
									(else (localproc_000c 81 28))
								)
							)
							((Said '/lid,cover,manhole,hole,sewer')
								(if (ego inRect: 0 160 132 200)
									(if (not manholeIsOpen)
										(if (not foundManhole)
											((= manholeCloseup (View new:))
												view: 261
												loop: 0
												cel: 3
												posn: 58 116
												init:
												stopUpd:
												ignoreActors:
											)
											(SolvePuzzle 1 100)
											(= foundManhole 1)
											(localproc_000c 81 29 83)
										else
											(localproc_000c 81 27)
										)
									else
										(localproc_000c 81 30)
									)
								else
									(localproc_000c 81 31)
								)
							)
						)
					)
					((Said 'extender/dispatch') (localproc_000c 81 32))
					((Said 'feed/duck') (localproc_000c 81 33))
					((Said 'get,kill,fire,eat/duck,egg') (localproc_000c 81 34))
					((Said 'chat/duck,egg') (localproc_000c 81 35))
					((Said 'dig[/hole,dirt]') (localproc_000c 81 36))
					((Said 'lie,lay/dirt,grass,lawn') (localproc_000c 81 37))
					(
						(or
							(Said 'get,jump,swim,walk,wade[<in]/pond,water')
							(Said 'swim[<go]')
						)
						(localproc_000c 81 38)
					)
					((Said 'drink/water') (localproc_000c 81 39))
					((Said 'get/rock') (localproc_000c 81 40))
					((Said 'hoist,move/rock') (localproc_000c 81 41))
					((Said 'climb/tree') (localproc_000c 81 42))
					((Said 'climb,jump/fence') (localproc_000c 81 43))
					((Said 'pick/flower') (localproc_000c 81 44))
					((Said 'get/grass,lawn') (localproc_000c 81 45))
					(
						(and
							(Said 'get,get/clue,cover,manhole,ladder')
							lookedDown
						)
						(localproc_000c 81 46)
					)
					((Said 'display/badge,badge') (localproc_000c 81 47))
					(
						(or
							(Said 'call,extender/backup,friend')
							(Said '(key<up),use/extender,(talkie<walkie)')
						)
						(if (ego has: 30)
							(if (not muggerFleeing)
								(switch (Random 1 4)
									(1
										(localproc_000c 81 48)
										(localproc_000c 81 49)
										(localproc_000c 81 50)
									)
									(2 (localproc_000c 81 51))
									(3 (localproc_000c 81 52))
									(4 (localproc_000c 81 53))
								)
							else
								(localproc_000c 81 54)
							)
						)
					)
					((Said 'read/right,miranda,miranda') (localproc_000c 81 6))
					((Said 'interrogate,chat/dude,mugger')
						(if muggerArrested
							(localproc_000c 81 55)
						else
							(localproc_000c 81 6)
						)
					)
					(
						(Said
							'open,hoist,remove,move,press/lid,cover,manhole,sewer'
						)
						(if (ego inRect: 40 170 102 189)
							(if (not manholeIsOpen)
								(coverScript changeState: 0)
							else
								(localproc_000c 81 56)
							)
						else
							(localproc_000c 81 31)
						)
					)
					((Said 'deposit,close,replace/cover,manhole')
						(if (ego inRect: 40 170 102 189)
							(if (not manholeIsOpen)
								(localproc_000c 81 57)
							else
								(localproc_000c 81 58)
							)
						else
							(localproc_000c 81 31)
						)
					)
					(
						(or
							(Said 'go,get,enter,climb,stair<down,in,hole')
							(Said 'stair,crawl<[in]/sewer')
							(Said 'climb/ladder,sewer')
						)
						(= climbingDown 1)
						(cond 
							(manholeIsOpen (exitScript changeState: 0))
							(lookedDown (not manholeIsOpen) (localproc_000c 81 59))
							(else (localproc_000c 81 60))
						)
					)
				)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(localproc_000c 81 61 25 8)
				(self cue:)
			)
			(1
				(ego setMotion: MoveTo 75 179 self)
			)
			(2
				(ego
					view: 261
					loop: 3
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(ego setLoop: -1 cycleSpeed: 0)
				(self cue:)
			)
			(4
				(ego illegalBits: -32768)
				(curRoom newRoom: 120)
			)
		)
	)
)

(instance entranceScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 261
					loop: 3
					posn: 75 179
					setPri: -1
					init:
					illegalBits: 0
					startUpd:
				)
				(= lookedDown 1)
				(= manholeIsOpen 1)
				(= climbingDown 1)
				(manhole cel: 1)
				(self cue:)
			)
			(1
				(ego cycleSpeed: 1 setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(ego
					view: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 95 179 self
				)
			)
			(3
				(ego setMotion: MoveTo 93 179 illegalBits: -28672)
				(= climbingDown 0)
				(HandsOn)
			)
		)
	)
)

(instance coverScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego inRect: 54 180 83 189)
					(localproc_000c 81 62)
				else
					(self cue:)
				)
			)
			(1
				(manhole setCel: 1)
				(cond 
					(
						(or
							(not foundManhole)
							(not (cast contains: manholeCloseup))
						)
						((= manholeCloseup (View new:))
							view: 261
							loop: 0
							cel: 4
							posn: 58 116
							init:
							stopUpd:
							ignoreActors:
						)
					)
					((cast contains: manholeCloseup) (manholeCloseup setCel: 4))
				)
				(SolvePuzzle 2 101)
				(self cue:)
			)
			(2
				(localproc_000c 81 63 83)
				(self cue:)
			)
			(3
				(= manholeIsOpen 1)
				(ego illegalBits: -28672)
			)
		)
	)
)

(instance duckScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(duck setLoop: 0 setMotion: MoveTo 34 145 self)
			)
			(1
				(duck setLoop: 4 setCycle: EndLoop self)
			)
			(2 (duck setCycle: BegLoop self))
			(3
				(duck setLoop: 1 setCycle: Forward setMotion: MoveTo -15 124)
			)
		)
	)
)

(instance doggieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= dogIsHere 1)
				((= dog (Actor new:))
					view: 191
					loop: 0
					setStep: 6 4
					init:
					setCycle: Forward
					setAvoider: (Avoider new:)
				)
				(dog posn: 0 185 setMotion: MoveTo 150 170 self)
			)
			(1
				(dog loop: 1 setMotion: MoveTo 0 170 self)
			)
			(2
				(dog dispose:)
				(= dogIsHere 0)
				(= dogIsGone 1)
			)
		)
	)
)
