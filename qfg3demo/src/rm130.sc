;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include game.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm130 0
)

(instance rm130 of Room
	(properties
		picture 1
	)
	
	(method (init)
		(LoadMany SOUND 2 7 8 9)
		(SetPort 0 0 200 320 0 0)
		(super init:)
		(self setScript: seeMeGo)
	)
	
	(method (dispose)
		(LoadMany FALSE FORCOUNT)
		(super dispose:)
	)
)

(instance seeMeGo of Script
	(properties)
	
	(method (doit)
		(if (< (self state?) 4) (Palette PALCycle 95 218 -1))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: INVIS_CURSOR TRUE)
				(music number: 1 play: self)
			)
			(1 (glint init: setCycle: EndLoop))
			(2
				(glint2 init: setCycle: CycleTo 6 1)
			)
			(3 (glint2 setCycle: EndLoop))
			(4
				(SetPort 0 0 190 320 10 0)
				(DrawPic 130 IRISIN)
				(gloryDude cel: 0 init:)
				(= seconds 2)
				(music number: 2 play:)
			)
			(5
				(gloryDude setCycle: EndLoop self)
			)
			(6
				(soundFx number: 7 play: self)
				(= ticks 15)
			)
			(7 (lFx init: setCycle: Forward))
			(8
				(lFx dispose:)
				(soundFx number: 8 play: self)
				(sFx init: setPri: 14 setCycle: Forward)
			)
			(9
				(sFx setLoop: 2 x: 206 y: 55 setCycle: EndLoop self)
				(soundFx number: 11 play:)
			)
			(10
				(sFx dispose:)
				(beam init: setPri: 14 setCycle: EndLoop self)
			)
			(11
				(soundFx number: 9 play:)
				(beam dispose:)
				(III init: setCycle: EndLoop self)
			)
			(12 (III setCycle: BegLoop self))
			(13
				(III x: 85 y: 121 loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(14
				(soundFx number: 3 play: self)
				(music fade:)
				(wagesSign init: cycleSpeed: 5 setCycle: EndLoop)
			)
			(15 (curRoom newRoom: 140))
		)
	)
)

(instance III of Prop
	(properties
		x 54
		y 100
		view 134
		loop 5
	)
)

(instance gloryDude of Prop
	(properties
		x 201
		y 135
		view 131
		loop 2
	)
)

(instance wagesSign of Prop
	(properties
		x 260
		y 175
		view 135
		cel 1
	)
)

(instance lFx of Prop
	(properties
		x 204
		y 29
		view 134
	)
)

(instance sFx of Prop
	(properties
		x 203
		y 57
		view 134
		loop 1
	)
)

(instance beam of Prop
	(properties
		x 164
		y 72
		view 134
		loop 3
	)
)

(instance glint of Prop
	(properties
		x 134
		y 34
		view 988
		loop 1
		cycleSpeed 8
	)
)

(instance glint2 of Prop
	(properties
		x 60
		y 155
		view 988
		cycleSpeed 8
	)
)
