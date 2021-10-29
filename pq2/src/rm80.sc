;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
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
	rm80 0
)

(local
	[local0 3]
	muggerIsHere
	dogIsHere
	dog
	dogIsGone
	local7
	[local8 4]
	muggerTimer
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm80 of Room
	(properties
		picture 80
		style $0000
	)
	
	(method (init)
		(super init:)
		(= local7 (Random 100 400))
		(if (and (not muggerFleeing) (not global236))
			(= muggerTimer (Random 30 60))
		)
		(User canInput: 1)
		(= gunNotNeeded 0)
		(= gunFireState 1)
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
		(ego
			posn: 311 169
			view: (if (not gunDrawn) 0 else 6)
			setPri: -1
			init:
			setMotion: MoveTo 0 169
			startUpd:
		)
	)
	
	(method (doit)
		(if (and global236 (== (curRoom script?) 0))
			(self setScript: (ScriptID 77))
		)
		(if (> local7 0) (-- local7))
		(if (and (not muggerFleeing) (> muggerTimer 1))
			(-- muggerTimer)
		)
		(if
			(and
				(== muggerTimer 1)
				(not gunDrawn)
				(ego inRect: 121 106 319 189)
				(not isHandsOff)
			)
			(self setScript: (ScriptID 77))
			(= muggerIsHere 1)
			(= muggerTimer 0)
		)
		(if (> (ego x?) 312) (curRoom newRoom: 79))
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
							(dogIsHere (localproc_000c 80 0))
							(dogIsGone (localproc_000c 80 1))
							(else (localproc_000c 80 2))
						)
					)
					((Said 'look>')
						(cond 
							((Said '/chamber') (localproc_000c 80 3))
							((Said '/dude,mugger') (localproc_000c 80 4))
							((Said '/sand') (localproc_000c 80 5))
							((Said '[<at,around][/!*,park]') (localproc_000c 80 6))
							((Said '/tree') (localproc_000c 80 7))
							((Said '/fence') (localproc_000c 80 8))
							((Said '/bush,brush,bush') (localproc_000c 80 9))
							((or (Said '<up') (Said '/air')) (localproc_000c 80 10))
							(
							(or (Said '<down') (Said '/dirt,grass,lawn,path')) (localproc_000c 80 11))
							((Said '/pond') (localproc_000c 80 12))
							((Said '/cloud') (localproc_000c 80 13) (localproc_000c 80 14))
							((Said '/rock') (localproc_000c 80 15))
							((Said '/flower') (localproc_000c 80 16))
							((Said '/pansy') (localproc_000c 80 17))
						)
					)
					((Said 'pick/flower') (localproc_000c 80 18))
					((Said 'get/grass,lawn') (localproc_000c 80 19))
					((Said 'dig[/hole,dirt]') (localproc_000c 80 20))
					((Said 'lie,lay[/lawn,grass,dirt]') (localproc_000c 80 21))
					((Said 'drink/water') (localproc_000c 80 22))
					((Said 'get/rock') (localproc_000c 80 23))
					((Said 'climb/tree') (localproc_000c 80 24))
					((Said 'climb,jump/fence') (localproc_000c 80 25))
					((Said 'hoist,move/rock') (localproc_000c 80 26))
					((Said 'display/badge,badge') (localproc_000c 80 27))
					(
						(or
							(Said 'call,extender/backup,friend')
							(Said '(key<up),use/extender,(talkie<walkie)')
						)
						(if (ego has: 30)
							(if (not muggerFleeing)
								(switch (Random 1 4)
									(1
										(localproc_000c 80 28)
										(localproc_000c 80 29)
										(localproc_000c 80 30)
									)
									(2 (localproc_000c 80 31))
									(3 (localproc_000c 80 32))
									(4 (localproc_000c 80 33))
								)
							else
								(localproc_000c 80 34)
							)
						)
					)
					((Said 'read/right,miranda,miranda') (localproc_000c 80 4))
					((Said 'interrogate,chat/dude,mugger')
						(if muggerArrested
							(localproc_000c 80 35)
						else
							(localproc_000c 80 4)
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
				(dog posn: 100 188 setMotion: MoveTo 320 179 self)
			)
			(1 (dog loop: 0) (self cue:))
			(2
				(dog dispose:)
				(= dogIsHere 0)
				(= dogIsGone 1)
			)
		)
	)
)
