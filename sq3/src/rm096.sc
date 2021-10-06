;;; Sierra Script 1.0 - (do not remove this comment)
(script# 96)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm096 0
)

(local
	printObj
)
(procedure (ElmoSpeaks &tmp temp0)
	(= temp0 (Random 1 2))
	(elmo setCel: temp0)
	(mouth setLoop: temp0 setCycle: Forward)
	(tail init:)
)

(procedure (ClearText)
	(if printObj
		(tail dispose:)
		(RedrawCast)
		(cls)
		(= printObj 0)
	)
	(elmo setCel: 0)
	(mouth setCel: 0 setLoop: 3 stopUpd:)
)

(instance rm096 of Room
	(properties
		picture 96
	)
	
	(method (init &tmp [temp0 50])
		(Load VIEW 130)
		(super init:)
		(elmo init:)
		(mouth init:)
		(= saveDisabled TRUE)
		(Display 96 0
			p_at 116 136
			p_font 600
			p_width 180
			p_color vLGREEN
		)
		(Display 96 1
			p_at 112 144
			p_font 600
			p_width 180
			p_color vYELLOW
		)
		(Display 96 2
			p_at 112 151
			p_font 600
			p_width 180
			p_color vYELLOW
		)
		(Display 96 3
			p_at 105 158
			p_font 600
			p_width 180
			p_color vYELLOW
		)
		(Display 96 4
			p_at 106 165
			p_font 600
			p_width 180
			p_color vYELLOW
		)
		(Display 96 5
			p_at 106 173
			p_font 600
			p_width 180
			p_color vLGREEN
		)
		(HandsOff)
		(= inCartoon TRUE)
		(self setScript: ElmoTalk)
	)
	
	(method (doit &tmp [temp0 50])
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (event type?)
			(if printObj
				(tail dispose:)
				(RedrawCast)
				(cls)
			)
			(= saveDisabled FALSE)
			(curRoom newRoom: 120)
		)
	)
)

(instance ElmoTalk of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(ElmoSpeaks)
				(= printObj
					(Print 96 6
						#at -1 45
						#width 200
						#font 600
						#dispose
					)
				)
				(= seconds 10)
			)
			(2
				(ClearText)
				(= seconds 4)
			)
			(3
				(ElmoSpeaks)
				(= printObj
					(Print 96 7
						#at -1 45
						#width 200
						#font 600
						#dispose
					)
				)
				(= seconds 15)
			)
			(4
				(ClearText)
				(= seconds 4)
			)
			(5
				(ElmoSpeaks)
				(= printObj
					(Print 96 8
						#at -1 45
						#width 200
						#font 600
						#dispose
					)
				)
				(= seconds 10)
			)
			(6
				(ClearText)
				(= seconds 4)
			)
			(7
				(ElmoSpeaks)
				(= printObj
					(Print 96 9
						#at -1 45
						#width 200
						#font 600
						#dispose
					)
				)
				(= seconds 10)
			)
			(8
				(ClearText)
				(= seconds 4)
			)
			(9
				(ElmoSpeaks)
				(= printObj
					(Print 96 10
						#at -1 45
						#width 200
						#font 600
						#dispose
					)
				)
				(= seconds 10)
			)
			(10
				(ClearText)
			)
			(11
				(curRoom setScript: 0)
			)
		)
	)
)

(instance elmo of Prop
	(method (init)
		(super init:)
		(self
			view: 130
			setLoop: 0
			setCel: 0
			posn: 163 37
			setPri: 5
			ignoreActors: TRUE
		)
	)
)

(instance mouth of Prop
	(method (init)
		(super init:)
		(self
			view: 130
			setLoop: 3
			setCel: 0
			posn: 163 33
			setPri: 6
			ignoreActors: TRUE
		)
	)
)

(instance tail of Prop
	(method (init)
		(super init:)
		(self
			view: 130
			setLoop: 4
			setCel: 0
			posn: 200 44
			setPri: 15
			ignoreActors: TRUE
		)
	)
)
