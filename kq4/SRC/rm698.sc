;;; Sierra Script 1.0 - (do not remove this comment)
(script# 698)
(include game.sh)
(use Main)
(use Sound)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room698 0
)

(local
	local0
	[credit 84]
)
(instance creditMusic of Sound
	(properties)
)

(instance Room698 of Room
	(properties
		picture 698
		style DISSOLVE
	)
	
	(method (init)
		(Load VIEW 898)
		(Load VIEW 899)
		(super init:)
		(User canControl: FALSE canInput: FALSE)
		(= inCutscene TRUE)
		(curRoom setScript: creditActions)
	)
	
	(method (dispose)
		(User canControl: TRUE canInput: TRUE)
		(= inCutscene FALSE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(cond 
				(
					(and
						(== (event type?) keyDown)
						(== (event message?) `#2)
					)
					(event claimed: TRUE)
					(DoSound SoundOn (not (DoSound SoundOn)))
				)
				(
					(or
						(== (event message?) `x)
						(== (event message?) `X)
					)
					(theGame restart:)
				)
				(else (curRoom newRoom: 120))
			)
		)
	)
)

(instance creditActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(creditMusic number: 2 play: self)
				((= credit (View new:))
					view: 898
					setPri: 0
					setLoop: 0
					setCel: 0
					x: 162
					y: 77
					init:
				)
				(= [credit 1] (View new:))
				([credit 1]
					view: 898
					setPri: 0
					setLoop: 0
					setCel: 1
					x: 162
					y: 111
					init:
				)
				(= seconds 4)
			)
			(1
				(credit dispose:)
				([credit 1] dispose:)
				(= [credit 2] (View new:))
				([credit 2]
					view: 898
					setPri: 0
					setLoop: 1
					setCel: 0
					x: 162
					y: 77
					init:
				)
				(= [credit 3] (View new:))
				([credit 3]
					view: 898
					setPri: 0
					setLoop: 1
					setCel: 2
					x: 162
					y: 112
					init:
				)
				(= seconds 3)
			)
			(2
				([credit 2] dispose:)
				(= [credit 4] (View new:))
				([credit 4]
					view: 898
					setPri: 0
					setLoop: 1
					setCel: 1
					x: 162
					y: 77
					init:
				)
				(= seconds 3)
			)
			(3
				([credit 4] dispose:)
				([credit 3] dispose:)
				(= [credit 6] (View new:))
				([credit 6]
					view: 898
					setPri: 0
					setLoop: 2
					setCel: 0
					x: 162
					y: 77
					init:
				)
				(= [credit 5] (View new:))
				([credit 5]
					view: 898
					setPri: 0
					setLoop: 2
					setCel: 1
					x: 162
					y: 99
					init:
				)
				(= [credit 7] (View new:))
				([credit 7]
					view: 898
					setPri: 0
					setLoop: 2
					setCel: 2
					x: 162
					y: 137
					init:
				)
				(= seconds 3)
			)
			(4
				([credit 6] dispose:)
				([credit 7] dispose:)
				([credit 5] dispose:)
				(= [credit 8] (View new:))
				([credit 8]
					view: 899
					setPri: 0
					setLoop: 0
					setCel: 0
					x: 162
					y: 77
					init:
				)
				(= [credit 9] (View new:))
				([credit 9]
					view: 899
					setPri: 0
					setLoop: 0
					setCel: 1
					x: 162
					y: 109
					init:
				)
				(= [credit 10] (View new:))
				([credit 10]
					view: 899
					setPri: 0
					setLoop: 2
					setCel: 0
					x: 162
					y: 145
					init:
				)
				(= seconds 5)
			)
			(5
				([credit 8] dispose:)
				([credit 9] dispose:)
				([credit 10] dispose:)
				(= [credit 11] (View new:))
				([credit 11]
					view: 898
					setPri: 0
					setLoop: 3
					setCel: 0
					x: 162
					y: 77
					init:
				)
				(= [credit 12] (View new:))
				([credit 12]
					view: 898
					setPri: 0
					setLoop: 4
					setCel: 0
					x: 162
					y: 109
					init:
				)
				(= [credit 8] (View new:))
				([credit 8]
					view: 898
					setPri: 0
					setLoop: 3
					setCel: 2
					x: 162
					y: 145
					init:
				)
				(= seconds 4)
			)
			(6
				([credit 11] dispose:)
				([credit 12] dispose:)
				([credit 8] dispose:)
				(= [credit 13] (View new:))
				([credit 13]
					view: 898
					setPri: 0
					setLoop: 3
					setCel: 1
					x: 162
					y: 77
					init:
				)
				(= [credit 14] (View new:))
				([credit 14]
					view: 898
					setPri: 0
					setLoop: 4
					setCel: 1
					x: 162
					y: 111
					init:
				)
				(= seconds 4)
			)
			(7
				([credit 13] dispose:)
				([credit 14] dispose:)
				(= [credit 15] (View new:))
				([credit 15]
					view: 899
					setPri: 0
					setLoop: 1
					setCel: 0
					x: 162
					y: 77
					init:
				)
				(= [credit 16] (View new:))
				([credit 16]
					view: 899
					setPri: 0
					setLoop: 1
					setCel: 1
					x: 162
					y: 109
					init:
				)
				(= [credit 17] (View new:))
				([credit 17]
					view: 899
					setPri: 0
					setLoop: 1
					setCel: 2
					x: 162
					y: 145
					init:
				)
				(= [credit 18] (View new:))
				(= seconds 4)
			)
			(8
				([credit 16] dispose:)
				([credit 17] dispose:)
				([credit 18]
					view: 899
					setPri: 0
					setLoop: 1
					setCel: 3
					x: 162
					y: 109
					init:
				)
				(= [credit 19] (View new:))
				([credit 19]
					view: 899
					setPri: 0
					setLoop: 1
					setCel: 4
					x: 162
					y: 145
					init:
				)
			)
			(9
				([credit 15] dispose:)
				([credit 18] dispose:)
				([credit 19] dispose:)
				(curRoom newRoom: 120)
			)
		)
	)
)
