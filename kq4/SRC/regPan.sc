;;; Sierra Script 1.0 - (do not remove this comment)
(script# PAN)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	regPan 0
)
(synonyms
	(kiss kiss embrace)
	(pan pan pan man pan pan person animal pan)
)

(local
	panView
	local1
	local2
	playLuteNotes
	musicNotes
	local5
)
(instance panCage of Cage
	(properties)
)

(instance panTheme of Sound
	(properties)
)

(instance luteMusic of Sound
	(properties
		number 54
	)
)

(instance regPan of Region
	(properties
	;	name "Pan's Region"
	)
	
	(method (init)
		(super init:)
		(if ((Inventory at: iSilverFlute) ownedBy: 201)
			(= panView 157)
			(= local5
				(+
					(* (- gameHours hourLastMetPan) 60)
					(- gameMinutes minutesLastMetPan)
				)
			)
			(if (and (<= (Random 1 100) 40) (>= local5 2))
				(panCage
					left: 0
					right: 319
					bottom: 189
					top: (curRoom horizon?)
					init:
				)
				(Load VIEW 157)
				(if (ego has: iLute)
					(Load VIEW 54)
					(Load VIEW 150)
				)
				(= pan (Actor new:))
				(pan
					posn: (Random 10 300) (Random (+ (curRoom horizon?) 20) 169)
					xStep: 2
					yStep: 1
					setCycle: Forward
					observeBlocks: panCage
					setScript: panActions
					setMotion: Wander 1500
					view: panView
					init:
					yourself:
				)
				(= musicNotes (Prop new:))
				(musicNotes
					view: 888
					cycleSpeed: 1
					ignoreActors:
					setCycle: Forward
					init:
				)
				(panTheme number: 7 loop: -1 play:)
			else
				(= pan FALSE)
			)
		else
			(= pan FALSE)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (cast contains: pan)
			(if
			(or (== (pan view?) 158) (== (pan view?) 157))
				(musicNotes
					setPri: (pan priority?)
					x: (+ (pan x?) 8)
					y: (- (pan y?) 22)
				)
			)
			(if (not (pan inRect: 0 0 319 219))
				(pan dispose:)
				(if (IsObject musicNotes) (musicNotes dispose:))
				(panTheme loop: 1 changeState:)
			)
		)
	)
	
	(method (dispose)
		((ScriptID 0 4) dispose: delete:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if
			(and (== (event type?) saidEvent) (cast contains: pan))
				(cond 
					((Said 'play/lute')
						(if (ego has: iLute)
							(if (== (pan view?) 157)
								(ego setScript: playLute)
								(playLute changeState: 1)
							else
								(Print 514 0)
							)
						else
							(Print 800 2)
						)
					)
					((Said 'play/flute')
						(if (ego has: iSilverFlute)
							(if (== (panTheme state?) 3)
								(Print 514 1)
							else
								(event claimed: FALSE)
							)
						else
							(Print 800 2)
						)
					)
					((Said 'hum') (Print 514 2))
					((Said 'kill/pan') (Print 514 3))
					((Said 'play<with/pan') (Print 514 4))
					((Said 'dance') (Print 514 5))
					((Said 'get/pan') (Print 514 6))
					((Said 'capture/pan') (Print 514 7))
					((Said 'deliver/lute')
						(if (ego has: iLute)
							(if local2
								(if (< (ego distanceTo: pan) 22)
									((Inventory at: iLute) moveTo: 201)
									(Print 514 8)
									((Inventory at: iSilverFlute) moveTo: ego)
									(theGame changeScore: 3)
									(ego setMotion: 0)
									(pan view: 158 setMotion: Wander 3000)
									(= panView 158)
									(panTheme number: 8 loop: -1 play:)
									(musicNotes show:)
								else
									(Print 800 1)
								)
							else
								(Print 514 9)
							)
						else
							(Print 800 2)
						)
					)
					((Said 'deliver>')
						(if (= inventorySaidMe (inventory saidMe:))
							(if (ego has: (inventory indexOf: inventorySaidMe))
								(Print 514 10)
							else
								(DontHave)
							)
						else
							(Print 514 11)
							(event claimed: TRUE)
						)
					)
					((Said 'kiss') (Print 514 12))
					((== (pan view?) 157)
						(cond 
							((Said 'look/pan') (Print 514 13))
							((Said 'converse[/pan]') (Print 514 14))
							((Said 'get/flute') (Print 514 15))
							((Said 'rob/flute') (Print 514 16))
						)
					)
					((== (pan view?) 158)
						(cond 
							((Said 'get,rob/lute') (Print 514 17))
							((Said 'look/pan') (Print 514 18))
							((Said 'converse[/pan]') (Print 514 19))
						)
					)
					((== (pan view?) 150)
						(cond 
							((Said 'converse[/pan]') (Print 514 20))
							((Said 'look/pan') (Print 514 21))
							((Said 'get/flute') (Print 514 15))
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance playLute of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(panTheme client: 0 stop:)
				(luteMusic play: self)
				(ego view: 54 setLoop: 0 setCycle: Forward)
				(= playLuteNotes (Prop new:))
				(playLuteNotes
					view: 888
					setPri: (ego priority?)
					cycleSpeed: 1
					setCycle: Forward
					posn: (+ (ego x?) 15) (- (ego y?) 25)
					init:
				)
				(pan
					view: 150
					setAvoider: Avoider
					setCycle: Walk
					setMotion: Follow ego 55
				)
				(musicNotes hide:)
			)
			(2
				(Print 514 21 #at -1 10 #draw)
				(ego view: 2 setLoop: -1 setCycle: Walk)
				(if (IsObject playLuteNotes) (playLuteNotes dispose:))
				(Face ego pan)
				(= local2 1)
				(HandsOn)
			)
		)
	)
)

(instance panActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 25))
			(1 (pan ignoreBlocks: panCage))
		)
	)
)
