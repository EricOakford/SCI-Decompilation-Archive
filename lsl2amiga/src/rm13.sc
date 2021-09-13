;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
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
)
(instance mPiss2 of Sound
	(properties
		number 26
		priority -1
	)
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
		(Load SOUND 26)
		(super init:)
		(mPiss2 init:)
		(aView1
			loop: 0
			cel: 0
			setPri: 1
			ignoreActors:
			addToPic:
		)
		(self setRegions: CITY setScript: rm13Script)
		(aPuddle
			setLoop: 2
			setCel: 0
			setPri: 6
			cycleSpeed: 3
			ignoreActors:
			init:
		)
		(if (== 3 (Random 1 3))
			(rm13Script changeState: 1)
			(aHead
				setLoop: 1
				setCel: 0
				setPri: 3
				cycleSpeed: 1
				init:
			)
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 198 187)
			)
			((== prevRoomNum 17)
				(ego posn: 198 187)
			)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance rm13Script of Script
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
			(1
				(= seconds (Random 10 20))
			)
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
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '(look<in),open,increase/(barrel,barrel)') ;EO: Fixed decompiler goof in Said Spec
			(if (not (ego inRect: 230 115 284 137))
				(NotClose)
			else
				(Print 13 0)
				(Print 13 1
					#at -1 130
				)
			)
		)
		(if (Said 'look/barrel,barrel')
			(Print 13 2)
		)
		(if (Said 'look/fence')
			(Print 13 3)
			(Print 13 4
				#at -1 130
			)
		)
		(if (or (Said 'look<in,through/fence') (Said 'look/hole'))
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
		(if (or (Said '//hole,fence') (Said '/hole,fence'))
			(Print 13 6)
		)
		(if (Said 'look>')
			(if (Said '/graffiti')
				(Print 13 7)
			)
			(if (Said '/sign')
				(Print 13 8)
			)
			(if (Said '[/barrel,alley,barrel,building,airport]')
				(Print 13 9)
				(Print 13 10)
			)
		)
	)
)

(instance pissScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(mPiss2 play:)
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

(instance aView1 of View
	(properties
		y 34
		x 276
		view 242
	)
)

(instance aPuddle of Prop
	(properties
		y 1117
		x 243
		view 242
	)
)

(instance aHead of Prop
	(properties
		y 1073
		x 198
		view 242
	)
)
