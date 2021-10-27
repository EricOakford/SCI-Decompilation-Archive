;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
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
	rm82 0
)

(local
	keithCaughtMugger
	[local1 2]
	local3
	dogIsHere
	dog
	dogIsGone
	dogTimer
	[local8 4]
	local12
	searchedTree
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm82 of Room
	(properties
		picture 82
		style $0000
	)
	
	(method (init)
		(super init:)
		(if (and (not muggerFleeing) (not global236))
			(= local12 (Random 30 60))
		)
		(= dogTimer (Random 100 400))
		(User canInput: 1)
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
		)
		(= gunNotNeeded 0)
		(= gunFireState 1)
		(ego
			posn: 130 189
			view: (if (not gunDrawn) 0 else 6)
			init:
			setMotion: MoveTo 130 175
			setPri: -1
			startUpd:
		)
		((View new:)
			view: 261
			loop: 0
			cel: 2
			posn: 58 127
			setPri: 8
			brTop: 112
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 261
			loop: 0
			cel: 2
			posn: 191 110
			setPri: 7
			brTop: 95
			init:
			stopUpd:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 261
			loop: 0
			cel: 0
			posn: 20 189
			setPri: 14
			init:
			stopUpd:
			addToPic:
		)
	)
	
	(method (doit)
		(if (and global236 (== (curRoom script?) 0))
			(self setScript: (ScriptID 77))
		)
		(if (> dogTimer 0) (-- dogTimer))
		(if (and (not muggerFleeing) (> local12 1))
			(-- local12)
		)
		(if
			(and
				(== local12 1)
				(not gunDrawn)
				(not isHandsOff)
				(ego inRect: 119 116 207 142)
			)
			(self setScript: (ScriptID 77))
			(= local3 1)
			(= local12 0)
		)
		(if (and (> (ego y?) 189) (not isHandsOff))
			(curRoom newRoom: 79)
		)
		(if
			(and
				(== dogTimer 0)
				muggerFleeing
				(not dogIsHere)
				(not dogIsGone)
			)
			(doggieScript changeState: 0)
		)
		(cond 
			((and (> (ego x?) 250) (not searchedTree))
				(= searchedTree 1)
				(localproc_000c 82 0)
				(ego setMotion: MoveTo 200 145)
			)
			((and (<= (ego x?) 250) searchedTree) (= searchedTree 0))
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
							(dogIsHere (localproc_000c 82 1))
							(dogIsGone (localproc_000c 82 2))
							(else (localproc_000c 82 3))
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/park,scenery]') (localproc_000c 82 4))
							((Said '/dude,mugger') (localproc_000c 82 5))
							((Said '/chamber') (localproc_000c 82 6))
							((Said '/tree') (localproc_000c 82 7))
							((Said '/earthworm') (localproc_000c 82 8))
							((Said '/sand') (localproc_000c 82 9))
							((Said '/bird,friend') (localproc_000c 82 10))
							((Said '/bush,brush,bush') (localproc_000c 82 11))
							((Said '/friend')
								(if keithCaughtMugger
									(localproc_000c 82 12)
								else
									(localproc_000c 82 5)
								)
							)
							((Said '/lamp,post') (localproc_000c 82 13))
							((or (Said '<up') (Said '/air')) (localproc_000c 82 14))
							(
							(or (Said '<down') (Said '/dirt,grass,lawn,path')) (localproc_000c 82 15))
							((Said '/flower') (localproc_000c 82 16))
							((Said '/nasturtium') (localproc_000c 82 17))
							((Said '/table') (localproc_000c 82 18))
							((Said '/fence') (localproc_000c 82 19))
							((Said '/cloud') (localproc_000c 82 20))
							((Said '/rock') (localproc_000c 82 21))
						)
					)
					((Said 'climb/tree') (localproc_000c 82 22))
					((Said 'sat/table') (localproc_000c 82 23))
					((Said 'get/table') (localproc_000c 82 24))
					((Said 'pick,jump/flower') (localproc_000c 82 25))
					((Said 'dig[/hole,dirt]') (localproc_000c 82 26))
					((Said 'lie,lay[/dirt,grass,lawn]') (localproc_000c 82 27))
					((Said 'get/grass,lawn') (localproc_000c 82 28))
					((Said 'climb/fence') (localproc_000c 82 29))
					((Said 'move,hoist/rock') (localproc_000c 82 30))
					((Said 'get/rock') (localproc_000c 82 31))
					((Said 'climb,get,fire/lamp,post') (localproc_000c 82 32))
					((Said 'read/miranda,miranda')
						(if keithCaughtMugger
							(SolvePuzzle 2)
							(localproc_000c 82 33)
						else
							(localproc_000c 82 5)
						)
					)
					((Said 'display/badge,badge') (localproc_000c 82 34))
					(
						(or
							(Said 'call,extender/backup,friend')
							(Said '(key<up),use/extender,(talkie<walkie)')
						)
						(if (ego has: 30)
							(if (not muggerFleeing)
								(switch (Random 1 4)
									(1
										(localproc_000c 82 35)
										(localproc_000c 82 36)
										(localproc_000c 82 37)
									)
									(2 (localproc_000c 82 38))
									(3 (localproc_000c 82 39))
									(4 (localproc_000c 82 40))
								)
							else
								(localproc_000c 82 41)
							)
						)
					)
					((Said 'read/right,miranda,miranda') (localproc_000c 82 5))
					((Said 'interrogate,chat/dude,mugger')
						(if muggerArrested
							(localproc_000c 82 42)
						else
							(localproc_000c 82 5)
						)
					)
					((Said '/earthworm') (localproc_000c 82 43))
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
				(dog posn: 100 200 setMotion: MoveTo 170 170 self)
			)
			(1
				(dog loop: 1 setMotion: MoveTo 100 200 self)
			)
			(2
				(dog dispose:)
				(= dogIsHere 0)
				(= dogIsGone 1)
			)
		)
	)
)
