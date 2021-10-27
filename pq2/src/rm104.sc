;;; Sierra Script 1.0 - (do not remove this comment)
(script# 104)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm104 0
)

(local
	dustedMirror
	local1
	gloveBoxClosed
	seeingGloveBox
	local4
	local5
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance plane of Actor
	(properties)
)

(instance gloveBox of View
	(properties)
)

(instance gloveBoxDoor of View
	(properties)
)

(instance rm104 of Room
	(properties
		picture 50
		style $0003
	)
	
	(method (init)
		(HandsOff)
		(User canInput: 1)
		(self setLocales: 153)
		(Load rsVIEW 257)
		(Load rsVIEW 74)
		(gloveBox
			view: 257
			loop: 2
			cel: 0
			posn: 264 189
			setPri: 0
			init:
		)
		(gloveBoxDoor
			view: 257
			loop: 3
			cel: 0
			posn: 260 182
			setPri: 0
			init:
		)
		(plane
			view: 74
			setLoop: 0
			setPri: 1
			init:
			setScript: planeScript
		)
		(= gloveBoxClosed 1)
		(= seeingGloveBox 1)
		(super init:)
		(self setScript: rm104Script)
	)
)

(instance rm104Script of Script
	(properties)
	
	(method (doit)
		(if (> local4 1) (-- local4))
		(if (== local4 1) (= local4 0) (rm104Script cue:))
		(if (> local5 1) (-- local5))
		(if (== local5 1) (= local5 0) (planeScript cue:))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(StatusLine enable:)
				(= local4 (Random 100 450))
			)
			(1 (planeScript changeState: 0))
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					(
						(or
							(Said 'get<out[/auto]')
							(Said 'exit[/auto]')
							(Said 'close/door')
						)
						(HandsOn)
						(curRoom newRoom: 14)
					)
					((Said 'get/vin,badge,number') (localproc_000c 104 0) (SolvePuzzle 1 77))
					(
					(or (Said 'use/tape') (Said 'get,hoist/print,clue'))
						(if (not (ego has: 19))
							(if dustedMirror
								(global123 setPri: 0)
								(localproc_000c 104 1 83)
								(SolvePuzzle 3)
								(ego get: 19)
								(global123 setPri: 15)
							else
								(localproc_000c 104 2)
							)
						else
							(localproc_000c 104 3)
						)
					)
					((Said 'open/compartment,box')
						(if gloveBoxClosed
							(gloveBox setPri: 12)
							(gloveBoxDoor setPri: 13)
						)
						(gloveBoxDoor setCel: 1)
						(= seeingGloveBox 0)
						(localproc_000c 104 4 83)
					)
					((Said 'close/compartment,box')
						(gloveBoxDoor setCel: 0)
						(gloveBox setPri: 0)
						(gloveBoxDoor setPri: 0)
						(= seeingGloveBox 1)
						(= gloveBoxClosed 1)
					)
					((Said 'look>')
						(cond 
							((Said '[<around,at,in][/auto]') (localproc_000c 104 5))
							((Said '/dirt,crumb,cookie,leaf') (localproc_000c 104 6))
							((Said '/vin,badge,number') (localproc_000c 104 0) (SolvePuzzle 1 77))
							((Said '/dash,console') (localproc_000c 104 7))
							((Said '/mirror')
								(if (or dustedMirror (ego has: 19))
									(localproc_000c 104 8)
								else
									(localproc_000c 104 9)
								)
							)
							((Said '/airplane') (localproc_000c 104 10))
							((Said '/handle[<door]') (localproc_000c 104 11))
							((Said '/pane') (localproc_000c 104 12))
							((Said '<in,in/compartment,box')
								(if seeingGloveBox
									(localproc_000c 104 13)
								else
									(localproc_000c 104 14)
								)
							)
							((Said '/compartment,box')
								(= gloveBoxClosed 0)
								(gloveBox setPri: 12)
								(gloveBoxDoor setPri: 13)
								(if (or gloveBoxClosed seeingGloveBox)
									(localproc_000c 104 15 83)
								else
									(localproc_000c 104 16)
								)
							)
							((Said '/knob,gear,shift,baton,gearshift') (localproc_000c 104 17))
							((Said '/column,wheel') (localproc_000c 104 18))
							((or (Said '/horn') (Said '/ring<horn')) (localproc_000c 104 19))
							((Said '/tray,ashtray') (localproc_000c 104 20))
							((Said '/floor') (localproc_000c 104 21))
							((Said '/bench') (localproc_000c 104 22))
							((Said '/door') (localproc_000c 104 23))
							((Said '/roof') (localproc_000c 104 24))
						)
					)
					((Said 'read,find,look/vin,badge,number') (localproc_000c 104 0))
					((Said 'frisk>')
						(cond 
							((Said '/compartment,box')
								(if seeingGloveBox
									(localproc_000c 104 25)
								else
									(localproc_000c 104 26)
								)
							)
							((Said '/auto') (localproc_000c 104 27))
							((Said '/bench') (localproc_000c 104 28))
						)
					)
					((or (Said 'dust>') (Said 'use,apply/powder>'))
						(if (ego has: 10)
							(global122 setPri: 0)
							(global120 setPri: 0)
							(cond 
								((Said '/handle[<door]') (localproc_000c 104 29 83))
								((Said '/compartment,box') (localproc_000c 104 30 83))
								((Said '/column') (localproc_000c 104 31))
								((Said '/knob,gear,shift,baton,gearshift') (localproc_000c 104 32))
								((or (Said '/horn') (Said 'ring[<horn]')) (localproc_000c 104 33 83))
								((Said '/pane') (localproc_000c 104 33 83))
								((Said '/tray,ashtray') (localproc_000c 104 34 83))
								((Said '/dash,console') (localproc_000c 104 35 83))
								((Said '/mirror')
									(if (not (ego has: 19))
										(localproc_000c 104 36)
										(= dustedMirror 1)
									else
										(localproc_000c 104 37)
									)
								)
							)
							(global122 setPri: 15)
							(global120 setPri: 15)
						else
							(localproc_000c 104 38)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance planeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(plane
					setCel: 1
					posn: 360 44
					xStep: 8
					setMotion: MoveTo 131 44 self
					init:
					startUpd:
				)
			)
			(1
				(plane stopUpd:)
				(= local5 80)
			)
			(2
				(plane
					setCel: 0
					posn: 149 10
					xStep: 4
					setMotion: MoveTo 335 10 self
					startUpd:
				)
			)
		)
	)
)
