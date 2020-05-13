;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm13 0
)

(local
	[local0 2]
	tookPiss
	aHead
	aPuddle
)
(instance rm13 of Room
	(properties
		picture 13
		horizon 50
		east 14
		south 17
		west 12
	)
	
	(method (init)
		(Load VIEW 242)
		(super init:)
		((View new:)
			view: 242
			loop: 0
			cel: 0
			posn: 276 34
			setPri: 1
			ignoreActors:
			addToPic:
		)
		(self setRegions: CITY setScript: rm13Script)
		((= aPuddle (Prop new:))
			view: 242
			setLoop: 2
			setCel: 0
			setPri: 6
			cycleSpeed: 3
			posn: 243 1117
			ignoreActors:
			init:
		)
		(if (== 3 (Random 1 3))
			(rm13Script changeState: 1)
			((= aHead (Prop new:))
				view: 242
				setLoop: 1
				setCel: 0
				setPri: 3
				posn: 198 1073
				cycleSpeed: 1
				init:
			)
		)
		(cond 
			((== prevRoomNum 0) (ego posn: 198 187))
			((== prevRoomNum 17) (ego posn: 198 187))
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance rm13Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(ego inRect: 254 112 258 115)
				(not tookPiss)
				(== (ego loop?) 0)
			)
			(= tookPiss TRUE)
			(ego setScript: pissScript)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds (Random 10 20)))
			(2
				(aHead posn: 198 73 setCycle: EndLoop self)
			)
			(3
				(aHead setCel: 0 posn: 198 1034)
				(= state 0)
				(= seconds (Random 30 90))
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '(look<in),open,increase/(barrel,barrel)') ;EO: Fixed decompiler goof in Said Spec
			(if (not (ego inRect: 230 115 284 137))
				(NotClose)
			else
				(Print 13 0)
				(Print 13 1 #at -1 152)
			)
		)
		(if (Said 'look/barrel,barrel')
			(Print 13 2)
		)
		(if (Said 'look/fence')
			(Print 13 3)
			(Print 13 4
				#at -1 152
			)
		)
		(if
		(or (Said 'look<in,through/fence') (Said 'look/hole'))
			(if (not (ego inRect: 192 96 203 108))
				(NotClose)
			else
				(Print 13 5)
				(if (not lookedThroughKnothole)
					(= lookedThroughKnothole TRUE)
					(theGame changeScore: 1)
				)
			)
		)
		(if
		(or (Said '//hole,fence') (Said '/hole,fence'))
			(Print 13 6)
		)
		(if (Said 'look>')
			(if (Said '/graffiti') (Print 13 7))
			(if (Said '/sign') (Print 13 8))
			(if (Said '[/barrel,alley,barrel,building,airport]')
				(Print 13 9)
				(Print 13 10)
			)
		)
	)
)

(instance pissScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(aPuddle posn: 243 117 setCycle: EndLoop self)
			)
			(1
				(NormalEgo)
				(aPuddle dispose:)
				(Print 13 11 #draw)
			)
		)
	)
)
