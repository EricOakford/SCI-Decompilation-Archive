;;; Sierra Script 1.0 - (do not remove this comment)
(script# 666)
(include game.sh)
(use Main)
(use LBRoom)
(use Scaler)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use System)

(public
	rm666 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm666 of LBRoom
	
	(method (init)
		(self setRegions: 90)
		(ego setScale: Scaler 100 100 0 10)
		(if (== prevRoomNum 520)
			(ego init: posn: 277 55 normalize: 732)
			(Bset 31)
		else
			(ego init: posn: 96 161 normalize: 732)
		)
		(self
			picture:
				(if ((Inventory at: iLantern) cel?)
					(if (== prevRoomNum 520) 735 else 730)
				else
					780
				)
		)
		(if ((Inventory at: iLantern) cel?)
			(Palette PALIntensity 0 255 0)
		)
		(theGame handsOff:)
		(super init:)
		(if ((Inventory at: iLantern) cel?)
			(WrapMusic pause: 0)
			(= local0 1)
			(if (== prevRoomNum 520)
				(self setScript: sEnterSouthLight520)
			else
				(self setScript: sEnterSouthLight)
			)
		else
			(theMusic2 number: 56 flags: 1 loop: -1 play:)
			(ego hide:)
			(LoadMany 132 82 53)
			(self setScript: sEnterDark)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== (self picture?) 780) ((Inventory at: iLantern) cel?))
			(theGame handsOff:)
			(theMusic2 fade:)
			(WrapMusic pause: 0)
			(= local0 1)
			(Palette PALIntensity 0 255 0)
			(if (== prevRoomNum 520)
				(self picture: 735 drawPic: 735)
			else
				(self picture: 730 drawPic: 730)
			)
			(if (== prevRoomNum 520)
				(ego
					view: 732
					setLoop: 5
					posn: 277 55
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setCycle: Walk
					show:
				)
			else
				(ego
					view: 732
					setLoop: 0
					posn: 96 161
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setCycle: Walk
					show:
				)
			)
			(= local3 1)
		)
		(if local0
			(Palette PALIntensity 0 255 (+= local2 2))
			(if (>= local2 100)
				(= local0 0)
				(if local3
					(if (== prevRoomNum 520)
						(self setScript: sEnterSouthLight520)
					else
						(self setScript: sEnterSouthLight)
					)
				)
			)
		)
		(if local1
			(Palette PALIntensity 0 255 (Max 0 (-= local2 3)))
			(if (== local2 0) (= local1 0))
		)
	)
	
	(method (dispose)
		(WrapMusic pause: 1)
		(theMusic2 fade:)
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(cond 
			((== n 99) 0)
			((== prevRoomNum 650)
				(if
					(and
						(== currentAct 3)
						(TriggerEvent -15612 1)
						(not (TriggerEvent -15612))
					)
					(= n 565)
				else
					(= n 560)
				)
			)
			((== prevRoomNum 630) (= n 454))
			((== prevRoomNum 520) (= n 610))
		)
		(super newRoom: n)
	)
)

(instance sEnterSouthLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3
					(= cycles 1)
				else
					(ego
						setLoop: 0
						posn: -10 240
						edgeHit: 0
						setCycle: Walk
						cycleSpeed: 4
						moveSpeed: 4
						xStep: 2
						setMotion: MoveTo 96 161 self
					)
				)
			)
			(1
				(ego setMotion: MoveTo 277 55 self)
			)
			(2
				(= local1 1)
				(ego setMotion: MoveTo 335 23 self)
			)
			(3
				(curRoom drawPic: 780)
				(curRoom newRoom: 0)
				(self dispose:)
			)
		)
	)
)

(instance sEnterSouthLight520 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3
					(= cycles 1)
				else
					(ego
						setLoop: 5
						posn: 335 23
						edgeHit: 0
						setCycle: Walk
						cycleSpeed: 4
						moveSpeed: 4
						xStep: 2
						setMotion: MoveTo 277 55 self
					)
				)
			)
			(1
				(ego setMotion: MoveTo 96 161 self)
			)
			(2
				(= local1 1)
				(ego setMotion: MoveTo -10 240 self)
			)
			(3
				(curRoom drawPic: 780)
				(curRoom newRoom: 0)
				(self dispose:)
			)
		)
	)
)

(instance sEnterDark of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: 15)
					(theGame handsOn:)
					(User canControl: 0)
					(User canInput: 0)
					(theIconBar disable: 0 1 2 3 4 5 7)
					(= seconds 8)
				else
					(theIconBar disable: 6)
					(= seconds 4)
				)
			)
			(1
				(sFX2 number: 53 loop: -1 flags: 1 play:)
				(= ticks 120)
			)
			(2
				(sFX number: 82 loop: 1 flags: 1 play: self)
			)
			(3 (sFX2 fade: self))
			(4
				(= deathReason 15)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance sFX2 of Sound
	(properties
		flags $0001
	)
)
