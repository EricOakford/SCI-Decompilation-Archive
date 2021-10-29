;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include sci.sh)
(use Main)
(use Intrface)
(use Wander)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm79 0
)

(local
	duck1
	duck2
	[local2 2]
	dogIsHere
	dog
	dogIsGone
	local7
	local8
	local9
	[local10 3]
	muggerTimer
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm79 of Room
	(properties
		picture 79
		style $0000
	)
	
	(method (init)
		(= local7 (Random 100 400))
		(Load rsVIEW 0)
		(Load rsVIEW 4)
		(Load rsVIEW 6)
		(Load rsVIEW 261)
		(Load rsVIEW 191)
		(Load rsVIEW 264)
		(if (not muggerFleeing)
			(Load rsVIEW 88)
			(Load rsVIEW 32)
			(Load rsVIEW 46)
			(Load rsVIEW 52)
		)
		(super init:)
		(if (and (not muggerFleeing) (not global236))
			(= muggerTimer (Random 90 120))
		)
		(User canInput: 1)
		(= gunNotNeeded 0)
		(= gunFireState 1)
		(ego
			setPri: -1
			view: (if (not gunDrawn) 0 else 6)
			setCycle: Walk
			init:
		)
		(switch prevRoomNum
			(78
				(ego posn: 117 199 setMotion: MoveTo 117 107)
			)
			(80
				(ego posn: 6 167 setMotion: MoveTo 315 167)
			)
			(81
				(ego posn: 316 159 setMotion: MoveTo 0 159)
			)
			(82
				(ego posn: 90 108 setMotion: MoveTo 93 189)
			)
		)
		((= duck1 (Actor new:))
			view: 264
			posn: 192 119
			init:
			setStep: 2 1
			setCycle: Walk
			moveSpeed: 2
			setMotion: Wander
		)
		((= duck2 (Actor new:))
			view: 264
			posn: 279 114
			init:
			setStep: 2 1
			setCycle: Walk
			moveSpeed: 2
			setMotion: Wander
		)
	)
	
	(method (doit)
		(cond 
			((> (ego y?) 200) (curRoom newRoom: 78))
			((< (ego y?) 107) (curRoom newRoom: 82))
			((> (ego x?) 317) (curRoom newRoom: 81))
			((< (ego x?) 5) (curRoom newRoom: 80))
		)
		(if (and global236 (== (curRoom script?) 0))
			(self setScript: (ScriptID 77))
		)
		(if (> local7 0) (-- local7))
		(if (and (not muggerFleeing) (> muggerTimer 1))
			(-- muggerTimer)
		)
		(if (== muggerTimer 1)
			(if
				(and
					(not gunDrawn)
					(not isHandsOff)
					(ego inRect: 37 148 166 189)
				)
				(self setScript: (ScriptID 77))
				(= local9 1)
			)
			(= muggerTimer 0)
		)
		(if
			(and
				(== local7 0)
				muggerFleeing
				(not dogIsHere)
				(not dogIsGone)
			)
			(doggieScript changeState: 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '[*]/dog')
						(cond 
							(dogIsHere (localproc_000c 79 0))
							(dogIsGone (localproc_000c 79 1))
							(else (localproc_000c 79 2))
						)
					)
					((Said 'look>')
						(cond 
							((Said '/chamber') (localproc_000c 79 3))
							((Said '[<at,around][/scenery,park]') (localproc_000c 79 4))
							((Said '/tree') (localproc_000c 79 5))
							((Said '/bush,brush,bush') (localproc_000c 79 6))
							((Said '<up,air') (localproc_000c 79 7))
							((Said '[<at,down][/dirt,grass,lawn,path]') (localproc_000c 79 8))
							((Said '/flower') (localproc_000c 79 9))
							((Said '/jonquil') (localproc_000c 79 10))
							((Said '/light,lamp') (localproc_000c 79 11))
							((Said '/cloud') (localproc_000c 79 12) (localproc_000c 79 13))
							((Said '/rock') (localproc_000c 79 14))
							((Said '/pond,water') (localproc_000c 79 15))
							((Said '/sidewalk') (localproc_000c 79 16))
							((Said '/dude,mugger') (localproc_000c 79 17))
							((Said '/duck,bird') (localproc_000c 79 18))
							((Said '/egg') (localproc_000c 79 19))
							((Said '/sand') (localproc_000c 79 20))
						)
					)
					(
						(or
							(Said 'get,jump,swim,walk,wade[<in]/pond,water')
							(Said 'swim[<go]')
						)
						(localproc_000c 79 21)
					)
					((Said 'pick/flower') (localproc_000c 79 22))
					((Said 'get/grass,lawn') (localproc_000c 79 23))
					((Said 'dig[/hole,grass,lawn,dirt]') (localproc_000c 79 24))
					((Said 'lay,lie[/dirt,grass,lawn]') (localproc_000c 79 25))
					((Said 'make/wish') (localproc_000c 79 26) (localproc_000c 79 27))
					((Said 'drink/water') (localproc_000c 79 28))
					((Said 'climb/tree') (localproc_000c 79 29))
					((Said 'feed/duck') (localproc_000c 79 30))
					((Said 'get,kill,fire,eat/duck,egg') (localproc_000c 79 31))
					((Said 'chat/duck,egg') (localproc_000c 79 32))
					((Said 'move,hoist,get/rock') (localproc_000c 79 33))
					((Said 'display/badge,badge') (localproc_000c 79 34))
					(
						(or
							(Said 'call,extender/backup,friend')
							(Said '(key<up),use/extender,(talkie<walkie)')
						)
						(if (ego has: 30)
							(if (not muggerFleeing)
								(switch (Random 1 4)
									(1
										(localproc_000c 79 35)
										(localproc_000c 79 36)
										(localproc_000c 79 37)
									)
									(2 (localproc_000c 79 38))
									(3 (localproc_000c 79 39))
									(4 (localproc_000c 79 40))
								)
							else
								(localproc_000c 79 41)
							)
						)
					)
					((Said 'read/right,miranda,miranda') (localproc_000c 79 17))
					((Said 'interrogate,chat/dude,mugger')
						(if muggerArrested
							(localproc_000c 79 42)
						else
							(localproc_000c 79 17)
						)
					)
				)
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
				(dog posn: 0 200 setMotion: MoveTo 120 161 self)
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
