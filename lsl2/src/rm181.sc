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
	waterfall
	triedToTurnBack
	oldEgoX
	oldEgoY
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
		((= waterfall (Prop new:))
			view: 727
			setLoop: 0
			posn: 27 188
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
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== currentStatus egoNormal)
				(or (== (ego edgeHit?) SOUTH) (== (ego edgeHit?) EAST))
			)
			(if (== triedToTurnBack FALSE) (= triedToTurnBack TRUE) (Print 181 0))
		else
			(= triedToTurnBack FALSE)
		)
		(if (== currentStatus egoNormal)
			(if (< (ego y?) 50)
				(ego setPri: 3)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			(
			(and (== (ego edgeHit?) WEST) (== currentStatus egoNormal)) (curRoom newRoom: 82))
			(
			(and (& (ego onControl:) $0002) (== currentStatus egoNormal)) (ego setPri: 8) (self changeState: 1))
			(
			(and (& (ego onControl:) $0004) (== currentStatus egoNormal)) (ego setPri: 0) (self changeState: 1))
			((== currentStatus 0) (= oldEgoX (ego x?)) (= oldEgoY (ego y?)))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Print 181 4 #at -1 20 #dispose)
				(= currentStatus egoFalling)
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
				(= currentStatus egoStopped)
				(Print 181 5)
				(= currentStatus egoDead)
				(if debugging
					(NormalEgo)
					(ego
						observeBlocks: blockEast blockSouth
						posn: oldEgoX oldEgoY
					)
					(self changeState: 0)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/hole,cloud,cloud,(boulder<through,in)')
				(Print 181 1)
				(Print 181 2 #at -1 152)
			)
			(if (Said '[/airport,/,cliff,cascade,fluid,brook]')
				(Print 181 3)
			)
		)
	)
)
