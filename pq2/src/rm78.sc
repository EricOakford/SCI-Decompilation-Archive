;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm78 0
)

(local
	pitman
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm78 of Room
	(properties
		picture 78
		style $0008
	)
	
	(method (init)
		(Load rsVIEW 0)
		(Load rsVIEW 4)
		(Load rsVIEW 6)
		(Load rsVIEW 190)
		(if (== prevRoomNum 101)
			(Load rsVIEW 50)
			(Load rsVIEW 20)
		)
		(super init:)
		(if (== (cSound state?) 3) (cSound stop:))
		(NormalEgo)
		(HandsOn)
		(if (Btst 140) (Bclr 140))
		((Prop new:)
			view: 190
			posn: 151 115
			loop: 0
			setCycle: Forward
			init:
			startUpd:
		)
		((View new:)
			view: 190
			posn: 48 188
			loop: 1
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		(switch prevRoomNum
			(79
				(ego
					posn: 102 111
					view: (if (not gunDrawn) 0 else 6)
					init:
					setMotion: MoveTo 102 189
					startUpd:
				)
			)
			(12 (ego init:))
			(else 
				((= pitman (Actor new:))
					view: 50
					loop: 1
					posn: 164 200
					init:
					stopUpd:
				)
				((= keith (Actor new:))
					view: 20
					loop: 0
					posn: 110 200
					init:
					stopUpd:
				)
				(ego view: 0 loop: 0 posn: 144 200 init: stopUpd:)
				(HandsOff)
				(pitman setScript: startScript)
			)
		)
	)
	
	(method (doit)
		(cond 
			(global236 (= global236 0) (localproc_000c 78 0))
			((< (ego y?) 110) (curRoom newRoom: 79))
			((< (ego x?) 3) (ego setMotion: MoveTo 150 (ego y?)))
			((and (>= (ego x?) 180) (> (ego y?) 190)) (ego setMotion: MoveTo 180 190))
			((> (ego y?) 215)
				(localproc_000c 78 1)
				(ego setMotion: MoveTo (ego x?) 180)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(startScript dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/(park,scenery)]') (localproc_000c 78 2))
							((Said '/chamber') (localproc_000c 78 3))
							((Said '/tree') (localproc_000c 78 4))
							((Said '/bush,brush,bush') (localproc_000c 78 5))
							((Said '[<up,at][/air]') (localproc_000c 78 6))
							((Said '[<down,at][/dirt,grass,lawn,path]') (localproc_000c 78 7))
							((Said '/flower') (localproc_000c 78 8))
							((Said '/marigold') (localproc_000c 78 9))
							((Said '/light,lamp') (localproc_000c 78 10))
							((Said '/cloud') (localproc_000c 78 11) (localproc_000c 78 12))
							((Said '/booth') (localproc_000c 78 13))
							((Said '/phone,number')
								(if (ego inRect: 185 170 240 189)
									(localproc_000c 78 14)
								else
									(localproc_000c 78 13)
								)
							)
							((Said '/fountain,water') (localproc_000c 78 15))
							((Said '/(bath<bird),birdbath') (localproc_000c 78 16))
							((Said '/well<wish') (localproc_000c 78 17))
							((Said '/sign') (localproc_000c 78 18))
							((Said '/friend')
								(if (> (keith x?) 0)
									(localproc_000c 78 19)
								else
									(localproc_000c 78 20)
								)
							)
							((Said '/sidewalk') (localproc_000c 78 21))
						)
					)
					(
						(or
							(Said 'call,extender/backup,friend')
							(Said 'use/extender,(walkie<talkie)')
						)
						(if (ego has: 30)
							(switch (Random 1 4)
								(1
									(localproc_000c 78 22)
									(localproc_000c 78 23)
									(localproc_000c 78 24)
								)
								(2 (localproc_000c 78 25))
								(3 (localproc_000c 78 26))
								(4 (localproc_000c 78 27))
							)
						else
							(localproc_000c 78 28)
						)
					)
					((Said 'make/wish') (localproc_000c 78 29) (localproc_000c 78 30))
					((Said 'dial,use,pick[<up]/phone')
						(if (& (ego onControl: 1) $0002)
							(localproc_000c 78 31)
						else
							(NotClose)
						)
					)
					((Said 'open,close/booth,door')
						(if (ego inRect: 185 170 240 189)
							(localproc_000c 78 32)
						else
							(NotClose)
						)
					)
					(
					(Said 'drink/water,fountain,(well[<wish]),(bath[<bird])') (localproc_000c 78 33))
					((Said 'climb/tree') (localproc_000c 78 34))
					((Said 'pick/flower') (localproc_000c 78 35))
					(
						(or
							(Said 'dig[/hole,dirt,grass]')
							(Said 'lie,lay[<down]/dirt,grass,lawn')
						)
						(localproc_000c 78 36)
					)
					((Said 'get,get/grass,lawn') (localproc_000c 78 37))
					((Said 'dust')
						(cond 
							((not (ego has: 10)) (localproc_000c 78 38))
							((& (ego onControl: 1) $0002) (localproc_000c 78 39))
							(else (localproc_000c 78 40))
						)
					)
				)
			)
		)
	)
)

(instance startScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(localproc_000c 78 41 83)
				(self cue:)
			)
			(2
				(pitman loop: 2 cel: 0 startUpd:)
				(= cycles 10)
			)
			(3
				(pitman loop: 1 cel: 0)
				(localproc_000c 78 42 83)
				(localproc_000c 78 43 83)
				(localproc_000c 78 44 83)
				(self cue:)
			)
			(4
				(pitman setMotion: MoveTo 164 240 self)
			)
			(5
				(if (ego has: 30)
					(localproc_000c 78 45)
				else
					(localproc_000c 78 46)
				)
				(self cue:)
			)
			(6
				(keith setCycle: Forward setMotion: MoveTo 0 240 startUpd:)
				(= gunNotNeeded 0)
				(= gunFireState 1)
				(ego startUpd:)
				(HandsOn)
				(pitman setScript: 0)
			)
		)
	)
)
