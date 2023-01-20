;;; Sierra Script 1.0 - (do not remove this comment)
(script# 238)
(include sci.sh)
(use Main)
(use SQRoom)
(use LoadMany)
(use Motion)
(use User)
(use System)

(public
	rmK3 0
)

(local
	local0
)
(instance rmK3 of SQRoom
	(properties
		picture 38
		style $0008
		horizon 125
	)
	
	(method (init)
		(switch (= local0 ((User alterEgo?) edgeHit?))
			(3
				(ego x: (Random 120 200))
				(= north prevRoomNum)
			)
			(1
				(ego x: (Random 120 200))
				(= south prevRoomNum)
			)
			(2
				(ego y: 170)
				(= west prevRoomNum)
			)
			(4
				(ego y: 170)
				(= east prevRoomNum)
			)
		)
		(LoadMany 128 18 420)
		(ego init:)
		(self setRegions: 704)
		(super init:)
	)
	
	(method (notify)
		(ego setScript: egoDead)
	)
)

(instance egoDead of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((not (User canControl:)) 0)
			((curRoom north?)
				(if
					(or
						(> (ego y?) 135)
						(< (ego x?) 30)
						(> (ego x?) 290)
					)
					(HandsOff)
					(ego setMotion: MoveTo (ego x?) 150 self)
				)
			)
			((curRoom south?)
				(if
					(or
						(< (ego y?) 170)
						(< (ego x?) 30)
						(> (ego x?) 290)
					)
					(HandsOff)
					(ego setMotion: MoveTo (ego x?) 160 self)
				)
			)
			((curRoom east?)
				(if
					(or
						(< (ego x?) 290)
						(< (ego y?) 140)
						(> (ego y?) 180)
					)
					(HandsOff)
					(ego setMotion: MoveTo 280 (ego y?) self)
				)
			)
			(
				(and
					(curRoom west?)
					(or
						(> (ego x?) 30)
						(< (ego y?) 140)
						(> (ego y?) 180)
					)
				)
				(HandsOff)
				(ego setMotion: MoveTo 30 (ego y?) self)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(if (curRoom script?)
					(self dispose:)
				else
					(HandsOff)
					(theMusic number: 810 loop: 1 play:)
					(ego
						view: 18
						setLoop: (if (< (ego heading?) 180) 0 else 1)
						cel: 0
						setMotion: 0
						cycleSpeed: 5
					)
					(= cycles 24)
				)
			)
			(2 (ego setCycle: End self))
			(3
				(EgoDead
					18
					1
					7
					{Whoa! Those big guys pack a powerful appetite. Did you feel the way that thing just chomped right through your skeletal system? That had to hurt! The grell burps in solitary satisfaction. He doesn't often get nice, warm meals like you.}
				)
			)
		)
	)
)
