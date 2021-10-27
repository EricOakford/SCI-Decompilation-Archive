;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm26 0
)

(local
	local0
	newView
	local2
	local3
	local4
)
(instance keithAct of Actor
	(properties)
)

(instance rm26 of Room
	(properties
		picture 26
		style $0004
	)
	
	(method (init)
		(++ global172)
		(if (== prevRoomNum 0)
			(= prevRoomNum 13)
			(= currentCar 13)
			(= gamePhase 12)
			(ego get: 0 1 2 3 10)
		)
		(User canInput: 1 canControl: 1)
		(= local3 0)
		(Load rsVIEW 252)
		(Load rsVIEW 1)
		(Load rsVIEW 0)
		(Load rsVIEW 20)
		(Load rsVIEW 129)
		(super init:)
		(self setLocales: 153)
		(= gunNotNeeded 0)
		(= gunFireState 3)
		((View new:)
			view: 252
			posn: 132 121
			loop: 1
			cel: 0
			setPri: 8
			init:
			stopUpd:
			ignoreActors: 0
		)
		((View new:)
			view: 252
			posn: 206 62
			loop: 0
			cel: 5
			ignoreActors:
			setPri: 0
			init:
			stopUpd:
		)
		((View new:)
			view: 252
			posn: 210 109
			loop: 0
			cel: 1
			setPri: 7
			init:
			stopUpd:
			ignoreActors: 0
		)
		((View new:)
			view: 252
			posn: 60 119
			loop: 0
			cel: 2
			setPri: 8
			init:
			stopUpd:
			ignoreActors: 0
		)
		((View new:)
			view: 252
			posn: 230 76
			loop: 0
			cel: 4
			setPri: 5
			init:
			stopUpd:
			ignoreActors: 0
		)
		((View new:)
			view: 252
			posn: 139 75
			loop: 0
			cel: 3
			setPri: 6
			init:
			stopUpd:
			ignoreActors: 0
		)
		(ego
			view: (if gunDrawn 6 else 0)
			loop: 1
			cel: 0
			posn: 220 170
			setPri: 0
			startUpd:
			illegalBits: -32768
			init:
		)
		(if (== currentCar 13)
			((= keith keithAct)
				view: 20
				loop: 3
				cel: 0
				posn: 220 180
				setPri: 0
				setCycle: Walk
				setMotion: Follow ego 500
				illegalBits: -32768
				init:
			)
		else
			(= keith 0)
		)
		(if (not (Btst 0))
			((= newView (View new:))
				view: 252
				posn: 202 119
				loop: 0
				cel: 0
				setPri: 7
				init:
				ignoreActors: 1
				stopUpd:
			)
			(self setScript: keithSpeak)
		else
			(self setScript: egoEnter)
		)
	)
	
	(method (doit)
		(if (ego inRect: 113 91 182 95)
			(= local0 1)
		else
			(= local0 0)
		)
		(super doit:)
		(cond 
			(
			(and (!= (mod (ego view?) 2) 0) (<= (ego y?) 100)) (ego view: (- (ego view?) 1)))
			(
			(and (!= (mod (ego view?) 2) 1) (> (ego y?) 100)) (ego view: (+ (ego view?) 1)))
		)
		(if (> local4 1) (-- local4))
		(if (not script)
			(if (>= (ego y?) 128)
				(if local4 (= local4 1) else (self newRoom: 25))
			)
			(if (and (== local4 1) (not local0))
				(self setScript: keithBack)
				(= local4 0)
			)
		)
	)
	
	(method (dispose)
		(cSound fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '[get]/crap')
						(if local0
							(Print 26 0 #at -1 20)
						else
							(Print 26 1 #at -1 20)
						)
					)
					((or (Said 'leak') (Said 'get/leak'))
						(if local0
							(Print 26 2 #at -1 20)
							(Print 26 3 #at -1 20)
						else
							(Print 26 1 #at -1 20)
						)
					)
					((Said 'flush/crapper')
						(if local0
							(Print 26 4 #at -1 20)
						else
							(Print 26 1 #at -1 20)
						)
					)
					((Said 'bath[/hand]')
						(if local0
							(Print 26 5 #at -1 20)
						else
							(Print 26 1 #at -1 20)
						)
					)
					((Said 'get/card')
						(if local0
							(if (not (InRoom 35))
								(Print 26 6)
							else
								(= local3 1)
								(Print 26 7 #at -1 20)
								(ego get: 35)
								(SolvePuzzle 3)
							)
						else
							(Print 26 8 #at -1 20)
						)
					)
					((Said 'read,open/letter,envelope')
						(if (ego has: 25)
							(Print 26 9 #at -1 130)
							(Print 26 10 #icon 125)
							(SolvePuzzle 2 89)
						else
							(Print 26 11 #at -1 130)
						)
					)
					((Said 'read,look/card')
						(if (not (ego has: 35))
							(event claimed: 0)
						else
							((inventory at: 35) showSelf:)
						)
					)
					(
						(or
							(Said 'turn/card')
							(Said 'look<back/card')
							(Said 'look/back/card')
						)
						(if (not (ego has: 35))
							(event claimed: 0)
						else
							(Print 26 12)
						)
					)
					((Said 'look>')
						(cond 
							(
							(or (Said '<below/table') (Said '<below/desk'))
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 14 #at -1 130)
								)
							)
							((Said '/basin')
								(if local0
									(if (== local3 1)
										(Print 26 15 #at -1 130)
									else
										(Print 26 16 #at -1 130)
									)
								else
									(Print 26 1 #at -1 20)
								)
							)
							((Said '/mirror')
								(if local0
									(Print 26 17 #at -1 130)
								else
									(Print 26 1 #at -1 20)
								)
							)
							((Said '/crapper')
								(if local0
									(Print 26 18 #at -1 130)
								else
									(Print 26 1 #at -1 20)
								)
							)
							((Said '<below/bed')
								(cond 
									(local0 (Print 26 13 #at 60 20))
									((not (ego inRect: 105 97 150 105)) (Print 26 19 #at 60 20))
									((InRoom 29) (Print 26 20))
									(else (Print 26 21))
								)
							)
							((Said '/bed')
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 22 #at -1 20)
								)
							)
							((Said '/lamp')
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 23 #at -1 20)
								)
							)
							((Said '/painting')
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 24 #at -1 130)
								)
							)
							((Said '/table,desk')
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 25 #at -1 20)
								)
							)
							((Said '/rope,cable,(trap[<booby])') (Print 26 26 #at -1 130) (Print 26 27 #at -1 130))
							((Said '/wall') (Print 26 28 #at -1 20))
							((Said '/hanger') (Print 26 29 #at -1 130))
							((or (Said '/ceiling') (Said '<up')) (Print 26 30 #at -1 20))
							(
							(or (Said '/floor,carpet,carpet') (Said '<down'))
								(if local0
									(Print 26 31 #at -1 20)
								else
									(Print 26 32 #at -1 130)
								)
							)
							(
							(or (Said '/nightstand') (Said '/stand[<night]'))
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 33 #at -1 130)
								)
							)
							((Said '/envelope') (if (ego has: 25) (Print 26 34) else (Print 26 35)))
							((Said '/letter')
								(if (ego has: 25)
									(Print 26 9 #at -1 130)
									(Print 26 10)
									(SolvePuzzle 2 89)
								else
									(Print 26 11 #at -1 130)
								)
							)
							((Said '/name,address')
								(if (not (ego has: 25))
									(Print 26 36 #at -1 20)
								else
									(Print 26 37 #at -1 130)
								)
							)
							((Said '/television')
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 38 #at -1 130)
								)
							)
							((Said '/bench')
								(if local0
									(Print 26 13 #at 60 20)
								else
									(Print 26 39 #at -1 130)
								)
							)
							((Said '/drawer')
								(cond 
									(local0 (Print 26 40 #at 60 20))
									((ego inRect: 55 115 80 125)
										(if (== local2 1)
											(if (ego has: 25)
												(Print 26 41)
											else
												(Print 26 42 #at -1 130)
												(Print 26 43 #icon 125)
											)
										else
											(Print 26 44 #at -1 130)
										)
									)
									(else (Print 26 45 #at -1 20))
								)
							)
							((Said '/blood,blood') (Print 26 46 #at -1 130))
							((Said '/bath') (if local0 (Print 26 47) else (Print 26 48)))
							((Said '[<at,around][/chamber,bathroom]')
								(if local0
									(Print 26 49 #at -1 130)
								else
									(Print 26 50 #at -1 130)
								)
							)
						)
					)
					((Said 'get/(baton<lip),lipstick,television')
						(cond 
							((ego has: 29) (Print 26 51))
							((not (InRoom 29)) (Print 26 52))
							((not (ego inRect: 105 97 150 105)) (NotClose))
							(else (ego get: 29) (Print 26 53) (SolvePuzzle 3))
						)
					)
					((Said 'turn<on/television')
						(if local0
							(Print 26 13 #at 60 20)
						else
							(Print 26 54 #at -1 20)
						)
					)
					((Said 'move/blanket')
						(if (ego inRect: 67 85 139 122)
							(Print 26 55 #at -1 130)
						else
							(Print 26 45 #at -1 20)
						)
					)
					((Said 'get/sample,blood')
						(cond 
							((not (ego has: 10)) (Print 26 56 #at -1 20))
							((Btst 113) (Print 26 57))
							((not (ego inRect: 108 108 150 126)) (NotClose))
							(else
								(global119 setPri: 0)
								(global118 setPri: 0)
								(Print 26 58 #draw)
								(SolvePuzzle 1)
								(ego get: 28)
								(global119 setPri: 13)
								(Bset 113)
							)
						)
					)
					((Said 'open/drawer')
						(if (ego inRect: 55 115 80 125)
							(if (== local2 0)
								(Print 26 59 #at -1 130)
								(if (ego has: 25)
									(Print 26 60 #at -1 130)
								else
									(= local2 1)
									(Print 26 61 #icon 125)
								)
							else
								(Print 26 62)
							)
						else
							(Print 26 45 #at -1 20)
						)
					)
					((Said 'close/drawer')
						(if (ego inRect: 55 115 80 125)
							(if (== local2 1)
								(Print 26 63 #at -1 130)
								(= local2 0)
							else
								(Print 26 64 #at -1 130)
							)
						else
							(Print 26 45 #at -1 20)
						)
					)
					((Said 'look,get/9mm,browning,(9mm<shot)')
						(cond 
							(local0 (Print 26 65))
							((Btst 0) (Print 26 66))
							(else (Print 26 67))
						)
					)
					((Said 'get/envelope,letter')
						(if (== local2 1)
							(if (ego inRect: 55 115 80 125)
								(if (ego has: 25)
									(Print 26 51)
								else
									(if (ego has: 24)
										(Print 26 68 #at -1 130)
										(Print 26 69 #at -1 130)
									else
										(Print 26 70)
									)
									(ego get: 25)
								)
							else
								(Print 26 45 #at -1 20)
							)
						else
							(Print 26 71 #at -1 20)
						)
					)
					((or (Said 'lie') (Said 'nap') (Said 'sat')) (Print 26 72))
				)
			)
		)
	)
)

(instance egoEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 10 setMotion: MoveTo 220 118 self)
				(if keith (keith setPri: 10 setMotion: MoveTo 220 122))
			)
			(1
				(ego setPri: -1)
				(if keith
					(keith setMotion: MoveTo 186 121 self)
				else
					(self cue:)
				)
			)
			(2
				(if keith (keith setPri: -1 setMotion: Follow ego 500))
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance keithSpeak of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 10 setMotion: MoveTo 220 118 self)
				(if keith
					(keith setPri: 10 setMotion: MoveTo 220 122)
					(Print 26 73 #dispose #at -1 20)
				)
			)
			(1
				(ego loop: 1 setPri: -1)
				(if keith
					(keith setPri: -1 setMotion: MoveTo 187 122 self)
				else
					(HandsOn)
					(client setScript: 0)
				)
			)
			(2
				(keith setMotion: MoveTo 187 118 self)
			)
			(3
				(cls)
				(Bset 0)
				(Print 26 74 #draw #at -1 130)
				(keith
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 220 122 self
				)
				(newView dispose:)
			)
			(4
				(= global172 1)
				(keith setPri: 10 setMotion: MoveTo 220 170 self)
			)
			(5
				(keith posn: 340 340)
				(= local4 500)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance keithBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(keith
					ignoreActors: 0
					posn: 220 170
					setMotion: MoveTo 220 135 self
				)
			)
			(1
				(Print 26 75 #at -1 130 #width 280 #draw)
				(keith setPri: -1 setMotion: Follow ego 500)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
