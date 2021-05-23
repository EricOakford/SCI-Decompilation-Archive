;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room15 0
)

(local
	local0
	local1
	local2
	local3
	local4
	firstEntry
)
(instance Room15 of Room
	(properties
		picture 15
	)
	
	(method (init)
		(= south 22)
		(= west 10)
		(= east 16)
		(= north 10)
		(= horizon 86)
		(= local0 0)
		(super init:)
		(= firstEntry (FirstEntry))
		(self
			setRegions: 206
			setFeatures: Window1 Window2 Window3
		)
		(LoadMany SOUND 43 44)
		(if
			(and
				(>= currentAct 1)
				(not (& deadGuests deadGERTRUDE))
				(not (& deadGuests deadLILLIAN))
			)
			(++ local1)
			(self setRegions: 235)
		)
		(Door
			cel: (if (== prevRoomNum 36) 3 else 0)
			ignoreActors: TRUE
			setPri: 6
			init:
			stopUpd:
		)
		(if howFast
			(Splash1 ignoreActors: TRUE init: hide:)
			(Splash2 ignoreActors: TRUE init: hide:)
		)
		(if (and (>= currentAct 2) (== global113 curRoomNum))
			(self setRegions: 202)
		)
		(if
			(and
				(== global114 curRoomNum)
				(or
					(== currentAct 3)
					(and (== currentAct 6) (not (& global118 $0002)))
				)
			)
			(self setRegions: 281)
		)
		(switch prevRoomNum
			(21 (ego posn: 93 187))
			(10 (ego posn: 8 155))
			(36
				(ego posn: 132 116)
				(if (not firstEntry)
					(= local4 1)
					(self setScript: comeDown)
				)
			)
			(22 (ego posn: 160 188))
			(else 
				(if (== global102 1)
					(ego posn: 317 152)
				else
					(ego posn: 317 185)
				)
			)
		)
		(ego view: 0 illegalBits: (| cWHITE cBLUE) init:)
	)
	
	(method (doit)
		(if firstEntry
			(Print 15 0)
			(if (== prevRoomNum 36)
				(= local4 1)
				(self setScript: comeDown)
			)
			(= firstEntry 0)
		)
		(if (and local4 (& (ego onControl: origin) cBLACK))
			(User canControl: TRUE)
			(ego illegalBits: cWHITE)
			(= local4 0)
		)
		(if
			(and
				(& (ego onControl: FALSE) cMAGENTA)
				(or (== (ego loop?) 3) (== (ego loop?) 0))
			)
			(User canControl: FALSE)
			(= local4 1)
			(ego illegalBits: 0 setMotion: MoveTo 253 153)
		)
		(if
			(and
				(& (ego onControl: origin) cLGREY)
				(or (== (ego loop?) 2) (== (ego loop?) 1))
			)
			(User canControl: FALSE)
			(= local4 1)
			(ego illegalBits: 0 setMotion: MoveTo 225 186)
		)
		(if
			(and
				(& (ego onControl: FALSE) cRED)
				(not local2)
				(or (== (ego loop?) 3) (== (ego loop?) 0))
			)
			(= local2 1)
			(self setScript: myDoor)
		)
		(if (< (ego y?) 162)
			(= global102 1)
		else
			(= global102 0)
		)
		(if
			(or
				(& (ego onControl: FALSE) cBLUE)
				(& (ego onControl: FALSE) cRED)
			)
			(ego setPri: 7)
		else
			(ego setPri: -1)
		)
		(if (& (ego onControl: origin) cBLUE)
			(curRoom newRoom: 36)
		)
		(if
			(and
				(& (ego onControl: origin) cYELLOW)
				(!= (ego mover?) 0)
				howFast
			)
			(switch (ego loop?)
				(2
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine,find/gertie,body')
						(if local1
							(event claimed: FALSE)
						else
							(Print 15 1)
						)
					)
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room<!*]')
								(Print 15 0)
							)
							((Said '/stair')
								(Print 15 2)
							)
							((Said '/up')
								(Print 15 3)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 36)
			(cSound stop:)
		)
		(super newRoom: n)
	)
)

(instance myDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego setMotion: MoveTo 119 131 self)
			)
			(1
				(ego setMotion: MoveTo 130 117 self)
			)
			(2
				(ego setMotion: 0 illegalBits: cWHITE)
				(Door cycleSpeed: 1 ignoreActors: 1 setCycle: EndLoop self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(3
				(ego setMotion: MoveTo (+ (ego x?) 50) (ego y?))
			)
		)
	)
)

(instance comeDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 3)
			)
			(1
				(ego setMotion: MoveTo 108 148 self)
				(Door cycleSpeed: 1 setCycle: BegLoop)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(2
				(HandsOn)
				(Door cycleSpeed: 0 stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 118
		x 148
		view 117
		loop 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {door})
		)
	)
)

(instance Splash1 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Splash2 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 20
		nsLeft 16
		nsBottom 87
		nsRight 39
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {window})
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 52
		nsLeft 175
		nsBottom 129
		nsRight 199
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {window})
		)
	)
)

(instance Window3 of RFeature
	(properties
		nsTop 54
		nsLeft 249
		nsBottom 135
		nsRight 275
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {window})
		)
	)
)

(instance myMusic of Sound)
