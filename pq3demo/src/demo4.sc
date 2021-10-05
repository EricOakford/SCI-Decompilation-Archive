;;; Sierra Script 1.0 - (do not remove this comment)
(script# 123)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo4 0
)

(local
	saveBits
	local1
)
(procedure (JimSays theString &tmp [temp0 3])
	(if saveBits
		(Display 123 0 p_restore saveBits)
		(= saveBits 0)
	)
	(if theString
		(= saveBits
			(Display theString
				p_width 130
				p_at 10 5
				p_mode teJustLeft
				p_font 4
				p_color 7
				p_save
			)
		)
	)
)

(instance demo4 of Room
	(properties
		picture 25
		style FADEOUT
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(redLine init:)
		(wheel setCycle: Forward init:)
		(indicator setCycle: Forward init:)
		(theMusic number: 252 loop: 1 play:)
		(self setScript: demoScript)
	)
	
	(method (doit &tmp temp0)
		(if script
			(script doit:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else
				(event claimed: TRUE)
			)
		)
	)
)

(instance demoScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(JimSays
					{In Police Quest III, you are Sonny Bonds. And like any cop, your job includes the routine...}
				)
				(= seconds 5)
			)
			(1
				(wheel stopUpd:)
				(indicator stopUpd:)
				(road init:)
				(roadSign init:)
				(redLights setCycle: Forward init:)
				(whiteLine setCycle: Forward init:)
				(pCar init:)
				(suspectCar init:)
				(= seconds 5)
			)
			(2
				(JimSays 0)
				(theMusic client: self fade: self)
			)
			(3
				(curRoom newRoom: 124)
			)
		)
	)
)

(instance road of Prop
	(properties
		x 240
		y 110
		view 1000
		priority 1
		signal (| fixPriOn stopUpdOn)
	)
)

(instance pCar of Prop
	(properties
		x 254
		y 102
		view 1000
		cel 1
		priority 3
		signal (| fixPriOn stopUpdOn)
	)
)

(instance suspectCar of Prop
	(properties
		x 254
		y 56
		view 1000
		cel 2
		priority 3
		signal (| fixPriOn stopUpdOn)
	)
)

(instance whiteLine of Prop
	(properties
		x 240
		y 109
		view 1000
		loop 1
		priority 2
		signal fixPriOn
		cycleSpeed 0
	)
)

(instance roadSign of Prop
	(properties
		x 223
		y 17
		view 1000
		loop 2
		priority 2
		signal (| fixPriOn stopUpdOn)
	)
)

(instance redLights of Prop
	(properties
		x 254
		y 89
		view 1000
		loop 3
		priority 15
		signal fixPriOn
		cycleSpeed 9
	)
)

(instance redLine of Prop
	(properties
		x 69
		y 66
		view 261
		loop 1
		signal fixPriOn
	)
	
	(method (doit)
		(super doit:)
		(self x: (+ 69 (Random 0 1)))
	)
)

(instance indicator of Prop
	(properties
		x 46
		y 65
		view 261
		loop 3
		priority 5
		signal fixPriOn
		cycleSpeed 14
	)
)

(instance wheel of Prop
	(properties
		x 139
		y 117
		view 261
		priority 5
		signal fixPriOn
		cycleSpeed 14
	)
)
