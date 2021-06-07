;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
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
	Room17 0
)

(local
	local0
	local1
	local2
	local3
	firstTime
)
(instance Room17 of Room
	(properties
		picture 17
	)
	
	(method (init)
		(super init:)
		(= firstTime (FirstEntry))
		(self
			setRegions: 206
			setFeatures: Window1 Window2 Window3 Window4
		)
		(LoadMany SOUTH 43 44)
		(= south 22)
		(= west 16)
		(= north 12)
		(= horizon 86)
		(= local0 0)
		(Door
			cel: (if (== prevRoomNum 38) 3 else 0)
			setPri: 6
			ignoreActors: TRUE
			init:
			stopUpd:
		)
		(if (== currentAct 1)
			(self setRegions: 381)
		)
		(switch prevRoomNum
			(16
				(if (== global102 1)
					(ego posn: 1 147)
				else
					(ego posn: 1 186)
				)
			)
			(38
				(ego posn: 190 115)
				(if (not (FirstEntry))
					(ego setMotion: MoveTo 216 141)
					(= local2 1)
					(Door setCycle: BegLoop)
					(User canControl: 0)
					(myMusic number: 44 loop: 1 priority: 5 play:)
				)
			)
			(23 (ego posn: 310 188))
			(12 (ego posn: 310 140))
		)
		(ego view: 0 illegalBits: (| cWHITE cBLUE) init:)
	)
	
	(method (doit)
		(if firstTime
			(Print 17 0)
			(if (== prevRoomNum 38)
				(ego setMotion: MoveTo 216 141)
				(= local2 1)
				(Door setCycle: BegLoop)
				(User canControl: FALSE)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(= firstTime 0)
		)
		(if
			(and
				(not local3)
				(== prevRoomNum 38)
				(== (Door cel?) 0)
			)
			(= local3 1)
			(Door stopUpd:)
		)
		(if (and local2 (& (ego onControl: origin) cBLACK))
			(User canControl: TRUE)
			(ego illegalBits: cWHITE)
			(= local2 0)
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
		(if (< (ego y?) 160)
			(= global102 1)
		else
			(= global102 0)
		)
		(if (== (ego edgeHit?) 2)
			(if (< (ego y?) 148)
				(curRoom newRoom: 12)
			else
				(curRoom newRoom: 18)
			)
		)
		(if (& (ego onControl: origin) cBLUE)
			(curRoom newRoom: 38)
		)
		(if
			(and
				(& (ego onControl: origin) cCYAN)
				(or (== (ego loop?) 3) (== (ego loop?) 1))
				(== local2 0)
			)
			(= local2 1)
			(User canControl: FALSE)
			(ego illegalBits: 0 setMotion: MoveTo 69 154)
		)
		(if
			(and
				(& (ego onControl: origin) cGREEN)
				(or (== (ego loop?) 2) (== (ego loop?) 0))
				(== local2 0)
			)
			(= local2 1)
			(User canControl: FALSE)
			(ego illegalBits: 0 setMotion: MoveTo 98 181)
		)
		(if
			(and
				(& (ego onControl: FALSE) cRED)
				(not local1)
				(or (== (ego loop?) 1) (== (ego loop?) 3))
			)
			(= local1 1)
			(self setScript: myDoor2)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 200)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (and (== (event type?) saidEvent) (Said 'examine>'))
				(cond 
					((Said '[<around,at][/room]')
						(Print 17 0)
					)
					((Said '/stair')
						(Print 17 1)
					)
					((Said '/up')
						(Print 17 2)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 38)
			(cSound stop:)
		)
		(super newRoom: n)
	)
)

(instance myDoor2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego setMotion: MoveTo 199 128 self)
			)
			(1
				(ego setMotion: MoveTo 189 114 self)
			)
			(2
				(ego setMotion: 0 illegalBits: cWHITE)
				(Door cycleSpeed: 1 ignoreActors: TRUE setCycle: EndLoop self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(3
				(ego setMotion: MoveTo (- (ego x?) 50) (ego y?))
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 119
		x 172
		view 117
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {door})
		)
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 53
		nsLeft 43
		nsBottom 139
		nsRight 69
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
		nsLeft 119
		nsBottom 121
		nsRight 142
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
		nsTop 27
		nsLeft 216
		nsBottom 89
		nsRight 234
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {window})
		)
	)
)

(instance Window4 of RFeature
	(properties
		nsTop 21
		nsLeft 278
		nsBottom 89
		nsRight 302
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {window})
		)
	)
)

(instance myMusic of Sound)
