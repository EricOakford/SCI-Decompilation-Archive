;;; Sierra Script 1.0 - (do not remove this comment)
(script# 181)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm181 0
)

(local
	turnBackMsg
	egoX
	egoY
)
(instance theSound of Sound
	(properties
		number 1
	)
)

(instance blockEast of Block
	(properties
		top 1
		left 328
		bottom 222
		right 333
	)
)

(instance blockSouth of Block
	(properties
		top 192
		left 99
		bottom 222
		right 333
	)
)

(instance rm181 of Room
	(properties
		picture 181
		horizon 33
		north 82
	)
	
	(method (init)
		(Load VIEW 727)
		(Load VIEW 103)
		(Load SOUND 1)
		(super init:)
		(theSound init:)
		(aWaterfall
			setCycle: Forward
			setPri: 3
			isExtra: TRUE
			init:
		)
		(blockEast init:)
		(blockSouth init:)
		(self setScript: rm181Script)
		(if (== prevRoomNum 82)
			(ego posn: 21 35)
		else
			(ego posn: 310 185)
		)
		(NormalEgo)
		(ego observeBlocks: blockEast blockSouth init:)
	)
)

(instance rm181Script of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(== currentStatus egoNORMAL)
				(or (== (ego edgeHit?) SOUTH) (== (ego edgeHit?) EAST))
			)
			(if (== turnBackMsg FALSE)
				(= turnBackMsg TRUE)
				(Print 181 0)
			)
		else
			(= turnBackMsg FALSE)
		)
		(if (== currentStatus egoNORMAL)
			(if (< (ego y?) 50)
				(ego setPri: 3)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			((and (== (ego edgeHit?) WEST) (== currentStatus egoNORMAL))
				(curRoom newRoom: 82)
			)
			((and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
				(ego setPri: 8)
				(self changeState: 1)
			)
			((and (& (ego onControl:) cGREEN) (== currentStatus egoNORMAL))
				(ego setPri: 0)
				(self changeState: 1)
			)
			((== currentStatus egoNORMAL)
				(= egoX (ego x?))
				(= egoY (ego y?))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Print 181 4 #at -1 20 #dispose)
				(= currentStatus egoFALLING)
				(theSound play:)
				(ego
					view: 103
					illegalBits: 0
					ignoreActors:
					ignoreBlocks: blockSouth blockEast
					posn: (ego x?) (- (ego y?) 15)
					cel: 0
					setStep: 1 15
					setCycle: Forward
					setMotion: MoveTo (ego x?) (+ (ego y?) 200) self
				)
			)
			(2
				(cls)
				(= currentStatus egoSTOPPED)
				(Print 181 5)
				(= currentStatus egoDYING)
				(if debugging
					(NormalEgo)
					(ego
						observeBlocks: blockEast blockSouth
						posn: egoX egoY
					)
					(self changeState: 0)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/hole,cloud,cloud,(boulder<through,in)')
				(Print 181 1)
				(Print 181 2 #at -1 130)
			)
			(if (Said '[/airport,cliff,cascade,fluid,brook]')
				(Print 181 3)
			)
		)
	)
)

(instance aWaterfall of Prop
	(properties
		y 188
		x 27
		view 727
	)
)
