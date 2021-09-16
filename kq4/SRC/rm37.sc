;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)

(public
	Room37 0
)

(local
	fairy1
	fairy2
)
(instance doorSound of Sound
	(properties)
)

(instance fairieTheme of Sound
	(properties
		number 33
		priority -1
	)
)

(instance door of Prop
	(method (cue)
		(if (!= (door cel?) (door lastCel:))
			(door setCycle: EndLoop self)
		else
			(HandsOn)
			(curRoom newRoom: 47)
		)
	)
)

(instance Room37 of Room
	(properties
		picture 37
	)
	
	(method (init)
		(= north 47)
		(= south 40)
		(= east 38)
		(= west 36)
		(= horizon 93)
		(= isIndoors FALSE)
		(if isNightTime
			(= picture 137)
		)
		(ego edgeHit: 0)
		(super init:)
		(self setRegions: GENESTA)
		(Load VIEW 613)
		(Load VIEW 2)
		(Load VIEW 114)
		(door
			view: 613
			loop: 0
			cel: 0
			posn: 158 93
			setPri: 5
			init:
			stopUpd:
		)
		(if (<= (ego y?) horizon) (ego y: (+ horizon 2)))
		(switch prevRoomNum
			(47
				(ego posn: 159 (+ horizon 3))
			)
			(36
				(ego posn: 1 (ego y?))
			)
			(38
				(ego posn: 317 (ego y?))
			)
			(40
				(ego posn: 157 188)
			)
		)
		(ego view: 2 xStep: 3 yStep: 2 init:)
		(= fairy1 (Actor new:))
		(fairy1
			ignoreHorizon:
			posn: 186 36
			view: 110
			setMotion: Wander 10
			illegalBits: cWHITE
			setCycle: Forward
			setPri: 15
			xStep: 1
			yStep: 1
			init:
		)
		(= fairy2 (Actor new:))
		(fairy2
			ignoreHorizon:
			posn: 107 37
			view: 114
			setMotion: Wander 10
			illegalBits: cWHITE
			setCycle: Forward
			setPri: 15
			xStep: 1
			yStep: 1
			init:
		)
		(Print 37 0)
		(fairieTheme play:)
	)
	
	(method (handleEvent event &tmp index)
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'look>')
					(cond 
						((Said '/bush')
							(Print 37 1)
						)
						((Said '/dirt')
							(Print 37 2)
						)
						((Said '/grass')
							(Print 37 3)
						)
						((Said '/flora')
							(Print 37 4)
						)
						((Said '/blossom')
							(Print 37 5)
						)
						((Said '/forest')
							(Print 37 6)
						)
						((Said '/garden')
							(Print 37 7)
						)
						((Said '/castle')
							(Print 37 8)
						)
						((Said '/beach')
							(Print 37 9)
						)
						((Said '/ocean,water')
							(Print 37 10)
						)
						((Said '/door')
							(Print 37 11)
						)
						((Said '/fairies')
							(Print 37 12)
						)
						((Said '/bird')
							(Print 37 13)
						)
						((Said '/path')
							(Print 37 14)
						)
						((Said '[<around][/room,island]')
							(Print 37 15)
						)
					)
				)
				((Said 'get/blossom')
					(Print 37 16)
				)
				((Said 'open/door')
					(if (ego inRect: 140 93 180 103)
						(ego setMotion: 0)
						(HandsOff)
						(doorSound number: 300 priority: 1 play: door)
						(door setCycle: EndLoop)
					else
						(NotClose)
					)
				)
				((or (Said 'kiss[/noword]') (Said 'kiss/fairies'))
					(Print 37 17)
				)
				((or (Said 'converse/fairies') (Said 'converse[/noword]'))
					(Print 37 18)
				)
				((Said 'bang')
					(if (ego inRect: 141 94 175 103)
						(Print 37 19)
					else
						(Print 37 20)
					)
				)
				((Said 'get,capture/fairies')
					(Print 37 21)
				)
				(
					(and
						(Said 'deliver>')
						(= index (inventory saidMe:))
					)
					(if (ego has: (inventory indexOf: index))
						(Print 37 22)
					else
						(DontHave)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(User canControl: TRUE)
		(super newRoom: n)
	)
)
