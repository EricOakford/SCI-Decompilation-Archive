;;; Sierra Script 1.0 - (do not remove this comment)
(script# 481)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm481 0
)

(local
	[plotString 200]
)
(procedure (PrintPlot &tmp seconds)
	(Print @plotString
		#at 0 70
		#width 125
		#time (= seconds (PrintDelay @plotString))
		#dispose
	)
	(return (+ 3 seconds))
)

(instance rm481 of Room
	(properties
		picture 485
		horizon 5
	)
	
	(method (init)
		(HandsOff)
		(Load PICTURE 99)
		(Load VIEW 54)
		(Load FONT 7)
		(Load SOUND 484)
		(Load SOUND 485)
		(StatusLine disable:)
		(TheMenuBar hide:)
		(music number: 484 loop: 2 play:)
		(super init:)
		(self setScript: RoomScript)
		(aThermometerP setCycle: EndLoop init:)
		(aThermometerL setCycle: EndLoop init:)
		(systemWindow color: 7 back: 0)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(theGame setSpeed: 6)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 33)
			)
			(1
				(Format @plotString 481 1)
				(= seconds (PrintPlot))
			)
			(2
				(Format @plotString 481 2)
				(= seconds (PrintPlot))
			)
			(3
				(Format @plotString 481 3)
				(= seconds (PrintPlot))
			)
			(4
				(Format @plotString 481 4)
				(= seconds (PrintPlot))
			)
			(5
				(Format @plotString 481 5)
				(= seconds (PrintPlot))
			)
			(6
				(curRoom drawPic: 99 IRISIN)
				(music number: 485 loop: 2 play:)
				(aThermometerP setLoop: 1 cycleSpeed: 0 setCycle: Forward)
				(aThermometerL setLoop: 1 cycleSpeed: 0 setCycle: Forward)
				(= cycles 55)
			)
			(7
				(Print 481 6 #font 7 #at 0 90 #width 125 #time 3 #dispose)
				(aThermometerP
					setLoop: 0
					setCel: 255
					cycleSpeed: 2
					setCycle: BegLoop
				)
				(aThermometerL
					setLoop: 0
					setCel: 255
					cycleSpeed: 2
					setCycle: BegLoop
				)
				(= cycles 55)
			)
			(8
				(aThermometerP dispose:)
				(aThermometerL dispose:)
				(= cycles 33)
			)
			(9
				(Format @plotString 481 7)
				(= seconds (PrintPlot))
			)
			(10 (curRoom newRoom: 482))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event claimed?) FALSE)
				(== (event message?) `#8)
			)
			(Print 481 0)
			(Bset fSkippedLoveScene)
			(curRoom newRoom: 484)
		)
	)
)

(instance aThermometerP of Prop
	(properties
		y 189
		x 181
		view 54
		cycleSpeed 12
	)
)

(instance aThermometerL of Prop
	(properties
		y 54
		x 101
		view 54
		cycleSpeed 12
	)
)
