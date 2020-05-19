;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm67 0
)

(instance rm67 of Room
	(properties
		picture 67
		horizon 35
		east 68
		west 66
	)
	
	(method (init)
		(LoadMany VIEW 4 7 56 66 136)
		(Load SCRIPT FALLING)
		(Load SOUND 17)
		(if (ego has: iMagicRing)
			(LoadMany VIEW 38 39)
		)
		(self style:
			(switch prevRoomNum
				(west
					WIPERIGHT
				)
				(else
					WIPELEFT
				)
			)
		)
		(super init:)
		(switch prevRoomNum
			(west
				(ego posn: 17 186 loop: 0)
			)
			(else  (ego posn: 299 56))
		)
		(Bset fLittleEgo)
		(ego init:)
		(NormalEgo)
		(walkway1 init:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script
				(script doit:)
			)
			((== (ego priority?) 1)
				(ego setPri: 2)
			)
			((== (ego onControl: origin) cCYAN)
				(self setScript: (ScriptID 613 0))
			)
			((== (ego onControl: origin) cRED)
				(self setScript: (ScriptID 613 1))
			)
			((== (ego onControl: origin) cGREY)
				(self setScript: (ScriptID 613 2))
			)
			((== (ego onControl: origin) cBLUE)
				(self setScript: (ScriptID 613 3))
			)
			((== (ego onControl: origin) cGREEN)
				(self setScript: (ScriptID 613 4))
			)
			((== (ego onControl: origin) cLGREEN)
				(self setScript: (ScriptID 613 5))
			)
			((== (ego onControl: origin) cYELLOW)
				(self setScript: (ScriptID 613 6))
			)
			((ego inRect: 0 50 15 61)
				(self setScript: goingLow)
			)
			((ego inRect: 0 71 15 83)
				(self setScript: goingHigh)
			)
			((== (ego onControl: origin) cLMAGENTA)
				(self newRoom: 68)
			)
			((== (ego onControl: origin) cLRED)
				(Bclr fLittleEgo)
				(Bclr fDwarfInCave)
				(self newRoom: 66)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event)
				(return)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(& (OnControl CMAP (event x?) (event y?)) cLCYAN)
					(User canInput:)
				)
				(event claimed: TRUE)
				(Print 67 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,cave]')
						(Print 67 1)
					)
					((Said '/catwalk,path')
						(Print 67 0)
					)
				)
			)
		)
	)
)

(instance goingLow of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(NormalEgo)
				(ego illegalBits: 0 loop: 0 cel: 0 posn: 17 78)
				(= seconds 2)
			)
			(1
				(ego setStep: 2 2 setMotion: MoveTo 30 78 self)
			)
			(2
				(ego illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goingHigh of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(NormalEgo)
				(ego illegalBits: 0 loop: 1 cel: 0 posn: 17 56)
				(= seconds 2)
			)
			(1
				(ego setStep: 2 2 setMotion: MoveTo 31 56 self)
			)
			(2
				(ego illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkway1 of NewFeature
	(properties
		x 164
		y 56
		noun 'catwalk<overhead'
		nsTop 50
		nsLeft 27
		nsBottom 63
		nsRight 301
		description {overhead walkway}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There is a wooden walkway suspended from the cave's ceiling.}
	)
)
