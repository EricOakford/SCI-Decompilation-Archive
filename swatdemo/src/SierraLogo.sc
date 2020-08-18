;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include game.sh)
(use Main)
(use PQRoom)
(use Motion)
(use Actor)
(use System)

(public
	sierraLogo 0
)

(class Star of Prop
	(properties
		priority 150
		fixPriority TRUE
		view 80
		loop 2
		cycleSpeed 2
		cueWho 0
	)
	
	(method (cue)
		(if cueWho
			(if (cueWho isKindOf: Star)
				(cueWho z: 0 setCycle: EndLoop cueWho)
			else
				(cueWho cue:)
			)
		)
		(self dispose:)
	)
)

(instance sierraLogo of PQRoom
	(properties
		picture 8
	)
	
	(method (init)
		(super init: &rest)
		(star1 init: z: 1000 cueWho: star2)
		(star2 init: z: 1000 cueWho: star3)
		(star3 init: z: 1000 cueWho: showLogo)
		(self setScript: showLogo)
	)
)

(instance showLogo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(theLogo init: setCycle: EndLoop self)
				(= cycles 1)
			)
			(2
				(theMusic number: 10 loop: 1 play:)
			)
			(3
				(theCompany init: setCycle: EndLoop self)
			)
			(4
				(= seconds 2)
			)
			(5
				(star1 z: 0 setCycle: EndLoop star1)
			)
			(6
				(= seconds 3)
			)
			(7
				(if (theMusic handle?)
					(-- state)
				)
				(= cycles 1)
			)
			(8
				(curRoom newRoom: 10)
			)
		)
	)
)

(instance theLogo of Prop
	(properties
		x 108
		y 109
		view 80
	)
)

(instance theCompany of Prop
	(properties
		y 1
		z 1
		view 80
		loop 1
	)
)

(instance star1 of Star
	(properties
		x 174
		y 45
	)
)

(instance star2 of Star
	(properties
		x 130
		y 77
	)
)

(instance star3 of Star
	(properties
		x 197
		y 161
	)
)
