;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include game.sh)
(use Main)
(use LLRoom)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm400 0
)

(local
	local0
	local1
	local2
)
(instance rm400 of LLRoom
	(properties
		picture 400
		east 405
		west 410
	)
	
	(method (init)
		(LoadMany VIEW 400 401)
		(LoadMany SOUND 401 402 403 404)
		(ego init:)
		(HandsOff)
		(switch prevRoomNum
			(east
				(ego posn: 285 100 normalize: 570)
				(ego setScript: sFromManager)
			)
			(else 
				(theMusic stop:)
				(theMusic2 stop:)
				(self style: 9)
				(ego loop: 3 cel: 2 posn: 195 96 normalize: 401)
				(ego setScript: sToManager)
			)
		)
		(super init:)
		(SetFFRoom 430)
	)
	
	(method (doit)
		(super doit:)
		(if (ego mover?)
			(switch (ego cel?)
				(0
					(if (== (stepSound number?) 404)
						(stepSound number: 402 play:)
					else
						(stepSound number: (+ (stepSound number?) 1) play:)
					)
				)
				(4
					(if (== (stepSound number?) 404)
						(stepSound number: 402 play:)
					else
						(stepSound number: (+ (stepSound number?) 1) play:)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade: 0 15 12 1)
		(super dispose: &rest)
	)
)

(instance patrons of Prop
	(properties
		x 183
		y 81
		view 400
		cycleSpeed 18
	)
	
	(method (doit)
		(cond 
			((< local2 4)
				(if (== (self cel?) 1)
					(if (not local1)
						(theMusic number: 401 flags: 1 loop: 0 play:)
						(++ local2)
						(= local1 1)
					)
				else
					(= local1 0)
				)
			)
			((== local2 4) (self setCycle: 0 stopUpd:))
		)
		(super doit:)
	)
)

(instance stepSound of Sound
	(properties
		number 404
	)
)

(instance sToManager of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(patrons init: setCycle: Forward)
				(ego setLoop: 0 setCycle: EndLoop self cycleSpeed: 12)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(ego view: 570 setLoop: 8 setCel: 6)
				(= seconds 4)
			)
			(3
				(Say ego 400 0 #at -1 15 #width 280)
				(= ticks 30)
			)
			(4
				(Say ego 400 1 #at -1 15 #width 280)
				(= ticks 30)
			)
			(5
				(Say ego 400 2 #at -1 15 #width 280)
				(= seconds 3)
			)
			(6
				(TimePrint 400 3 #at -1 185)
				(= seconds 3)
			)
			(7
				(Say ego 400 4 #at -1 15 #width 280)
				(ego
					normalize:
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 270 95 self
				)
			)
			(8
				(Say ego 400 5)
				(TimePrint 400 6 #at -1 185)
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance sFromManager of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 160 100 self
				)
			)
			(1
				(Say ego 400 7)
				(ego setMotion: MoveTo 32 100 self)
			)
			(2
				(TimePrint 400 8)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)
