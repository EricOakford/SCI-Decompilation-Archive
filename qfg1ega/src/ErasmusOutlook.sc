;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm28 0
)

(local
	sawGreenSign
	sawRedSign
)
(instance redSign of Prop
	(properties
		view vWizardWarning
	)
)

(instance greenSign of Prop
	(properties
		view vWizardWarning
		loop 1
	)
)

(instance Magic of Prop
	(properties
		view vTeleportPink
		cycleSpeed 2
	)
)

(instance rm28 of Room
	(properties
		picture 28
		horizon 115
		north 29
		west 27
	)
	
	(method (init)
		(Load VIEW vWizardWarning)
		(Load VIEW vTeleportPink)
		(rm28 style:
		(switch prevRoomNum
			(27 WIPERIGHT)
			(else  16)
		))
		(super init:)
		(cSound fade:)
		(StatusLine enable:)
		(NormalEgo)
		(ego init:)
		(switch prevRoomNum
			(27
				(ego posn: 1 160 setMotion: MoveTo 15 160)
			)
			(else 
				(if (not (Btst fErasmusWarpOut))
					(ego posn: 254 123 setMotion: MoveTo 254 130)
				)
			)
		)
		(if (Btst fErasmusWarpOut)
			(Magic
				posn: 98 160
				setPri: (+ (ego priority?) 1)
				ignoreActors:
				init:
			)
			(ego setScript: teleport)
		)
		(greenSign posn: 219 141 init:)
		(redSign setPri: 3 z: 23 posn: 251 124 init:)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(== (ego loop?) 0)
					(not sawGreenSign)
					(== (ego onControl: origin) cLRED)
				)
				(= sawGreenSign TRUE)
				(greenSign setScript: showGreenSign)
			)
			(
				(and
					(!= (ego loop?) 2)
					(not sawRedSign)
					(== (ego onControl: origin) cLCYAN)
				)
				(= sawRedSign TRUE)
				(redSign setScript: showRedSign)
			)
			((and (== (ego onControl: origin) cYELLOW) sawRedSign)
				(= sawRedSign FALSE)
			)
			(
				(and
					sawGreenSign
					(or
						(== (ego onControl: origin) cLMAGENTA)
						(== (ego onControl: origin) cYELLOW)
					)
				)
				(showGreenSign cue:)
			)
			((and (== (ego onControl: origin) cBLACK) sawGreenSign)
				(= sawGreenSign FALSE)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn28)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'spit')
						(Bset fErasmusWarpOut)
						;EO - Nothing obvious happens at first, but when you exit and return, it acts like you were warped out by Erasmus.
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*]')
								(HighPrint 28 0)
								;The air is crisp here and you can see your breath as you walk.
								;There is a path leading up to a dark mountain, atop which is precariously perched a purple house.
							)
							((Said '/path,road')
								(HighPrint 28 1)
								;The path seems to be carved out of the rock.  It forms a steep trail up to the house on the mountain.
							)
							((Said '/hill,peak')
								(HighPrint 28 2)
								;All of the mountains you can see are covered with snow except for the one to which the path leads.
							)
							((Said '/ice')
								(HighPrint 28 3)
								;The drifts of snow look several feet high in some places.
							)
							((Said '/north,mansion,house')
								(HighPrint 28 4)
								;Looking north, you see the strange house nestled on its craggy peak.  It is hard to see much at this distance.
								;All you can tell is that the house is very large, very purple, and very strange.
							)
							((Said '/forest,west')
								(HighPrint 28 5) ;EO - Text mistakenly says "east" when it should say "west".
								;To the east is the forest.
							)
							((Said '/east,south')
								(HighPrint 28 6)
								;You see the surrounding mountains.
							)
							((or (Said '<up') (Said '/cloud,sky'))
								(HighPrint 28 7)
								;The clouds skirt the mountain edges.
							)
							((or (Said '<down') (Said '/ground'))
								(HighPrint 28 8)
								;There is a rocky path through the snow.
							)
						)
					)
					((Said 'throw/')
						(HighPrint 28 9)
						;There is nothing here to throw it at.
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 28 10)
									;You detect a strange, magical aura in this place.
								)
							)
							(DAZZLE
								(if (CastSpell spell)
									(HighPrint 28 11)
									;There's nothing here to dazzle.
								)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(HighPrint 28 12)
									;The magical aura around this place prevents you from casting a flame dart.
								)
							)
							(else  (event claimed: FALSE))
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance showGreenSign of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(greenSign ignoreActors: FALSE setCycle: EndLoop self)
			)
			(1
				(if (not (Btst fSawGreenSign))
					(HighPrint 28 13)
					;A sign appears.  It reads:
					)
				(switch (Random 1 3)
					(1
						(HighPrint 28 14)
						;"Willkommen auf Zauberberg!"
						)
					(2
						(HighPrint 28 15)
						;"Welcome to Magic Mountain!"
						)
					(3 
						(HighPrint 28 16)
						;"Bienvenu a Mont Magie!
						)
				)
				(Bset fSawGreenSign)
			)
			(2
				(greenSign ignoreActors: setCycle: BegLoop setScript: 0)
			)
		)
	)
)

(instance showRedSign of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(redSign ignoreActors: FALSE setCycle: EndLoop self)
			)
			(1
				(ego setMotion: FALSE)
				(if (not (Btst fSawRedSign))
					(HighPrint 28 17)
					;Another sign appears.  It reads:
					)
				(switch (Random 1 3)
					(1
						(HighPrint 28 18)
						;"Trespassers will be toad!"
					)
					(2
						(HighPrint 28 19)
						;"Now go home!"
					)
					(3
						(HighPrint 28 20)
						;"Proceed at your own risk!"
					)
				)
				(Bset fSawRedSign)
				(redSign ignoreActors: setCycle: BegLoop setScript: 0)
			)
		)
	)
)

(instance teleport of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 98 160 loop: 2 cel: 1 hide:)
				(Magic setCycle: CycleTo 6 1 self)
			)
			(1
				(ego show:)
				(Magic setCycle: EndLoop self)
			)
			(2
				(Bclr fErasmusWarpOut)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)
