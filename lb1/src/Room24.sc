;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room24 0
)
(synonyms
	(attorney person fellow)
)

(local
	local0
	local1
	local2
)
(instance Room24 of Room
	(properties
		picture 24
	)
	
	(method (init)
		(= west 23)
		(= east 8)
		(super init:)
		(self setFeatures: Hedge Gazebo)
		(if
			(and
				(>= currentAct 3)
				(not (& deadGuests deadGLORIA))
				(not (& deadGuests deadLILLIAN))
			)
			(if (and (!= gCurRoomNum 23) (!= gCurRoomNum curRoomNum))
				(switch (Random 1 2)
					(1 (= gCurRoomNum curRoomNum))
					(2 (= gCurRoomNum 23))
				)
			)
			(if (and (== currentAct 3) (< global115 7))
				(= gCurRoomNum 24)
			)
			(if (== gCurRoomNum curRoomNum)
				(= local2 1)
				(self setRegions: 263)
			)
		)
		(if
			(and
				(or (== global154 5) (== [global368 1] 1))
				(== currentAct 1)
			)
			(Clarence init: setScript: clarActions)
			(LoadMany 143 243 248)
			(Load VIEW 906)
			(= global208 64)
			(= [global377 6] 248)
		)
		(switch prevRoomNum
			(18 (ego posn: 10 135))
			(8 (ego posn: 315 175 loop: 1))
			(30 (ego posn: 272 188))
			(29 (ego posn: 34 188))
			(23 (ego posn: 10 150))
		)
		(ego view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 24 0)
		)
		(switch (ego onControl: 0)
			(cBLUE
				(curRoom newRoom: 18)
			)
			(cRED
				(if (and (not local1) (not local2))
					(= local1 1)
					(User canControl: FALSE)
					(if (< (ego y?) 150)
						(ego setMotion: MoveTo 189 172)
					else
						(ego setMotion: MoveTo 197 126)
					)
				)
			)
			(else 
				(if local1 (= local1 0) (User canControl: 1))
			)
		)
		(if (== (ego edgeHit?) SOUTH)
			(if (< (ego x?) 159)
				(curRoom newRoom: 29)
			else
				(curRoom newRoom: 30)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(if
			(and
				global208
				(Said 'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>')
			)
			(self setScript: (ScriptID 243 0))
			((self script?) handleEvent: event)
			(if (event claimed?) (return (event claimed?)))
		)
		(return
			(if (== (event type?) saidEvent)
				(if
					(and
						global208
						(Said
							'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
						)
					)
					(self setScript: (ScriptID 243 0))
					((self script?) handleEvent: event)
					(if (event claimed?) (return (event claimed?)))
				)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]') (Print 24 0))
							((Said '/path') (Print 24 1))
							((Said '/stair,stair') (Print 24 2))
							((Said '[<down][/dirt]')
								(if (& (ego onControl: 0) cYELLOW)
									(Print 24 3)
								else
									(event claimed: FALSE)
								)
							)
							((or (Said '/ceiling') (Said '<up'))
								(if (& (ego onControl: 0) cYELLOW)
									(Print 24 4)
								else
									(event claimed: FALSE)
								)
							)
						)
					)
					((Said 'climb/stair')
						(Print 24 5)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance clarActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Clarence loop: 0 cel: 0 cycleSpeed: 1 setCycle: EndLoop)
				(= seconds 4)
			)
			(1
				(Clarence loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(Clarence setCycle: BegLoop)
				(= seconds (Random 5 10))
			)
			(3
				(Clarence loop: 2 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(Clarence setCycle: 0)
				(if (< (Random 1 100) 35)
					(= state 0)
				else
					(= state 2)
				)
				(= seconds (Random 5 15))
			)
		)
	)
)

(instance Clarence of Prop
	(properties
		y 167
		x 191
		view 412
		cel 1
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((and (MousedOn self event shiftDown) (not (& global207 $0040)))
				(event claimed: TRUE)
				(ParseName {clarence})
			)
			(
				(and
					(& global207 $0040)
					(or
						(MousedOn self event shiftDown)
						(Said 'examine/attorney,attorney')
					)
				)
				(event claimed: TRUE)
				(Print 24 6)
			)
			((Said 'converse/attorney,attorney')
				(= theTalker talkCLARENCE)
				(Say 1 24 7)
			)
		)
	)
)

(instance Hedge of RFeature
	(properties
		nsTop 97
		nsBottom 115
		nsRight 48
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine/garden,bush')
			)
			(event claimed: TRUE)
			(Print 24 8)
		)
	)
)

(instance Gazebo of RFeature
	(properties
		nsTop 3
		nsLeft 148
		nsBottom 152
		nsRight 267
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<in/gazebo')
				(Print 24 9)
			)
			((Said 'examine<below/gazebo')
				(Print 24 10)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/gazebo'))
				(event claimed: TRUE)
				(Print 24 11)
			)
		)
	)
)
