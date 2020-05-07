;;; Sierra Script 1.0 - (do not remove this comment)
(script# 190)
(include sci.sh)
(use Main)
(use KQ6Print)
(use Kq6Procs)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	openBook 0
	spellBookScr 1
	makeRainScript 2
)

(local
	theCast
	theFeatures
	theAddToPics
	theMouseDownHandler
	theKeyDownHandler
	theDirectionHandler
	theWalkHandler
	curRoomObstacles
	local8
	local9
	[local10 4]
	local14 =  110
	local15 =  1
	[local16 200]
	[local216 200]
	[local416 200]
	egoCel
)
(instance openBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= egoCel (ego cel?))
				(= seconds 2)
			)
			(1
				(ego
					normal: 0
					view: 903
					cel: 0
					setLoop: 2
					cycleSpeed: 5
					setCycle: End self
				)
			)
			(2
				(ego cel: 0 setLoop: 0 setCycle: End self)
			)
			(3
				(client setScript: (ScriptID 190 1))
			)
		)
	)
)

(instance spellBookScr of Script
	(properties)
	
	(method (init)
		(theGame handsOff:)
		(User canInput: 1)
		(= theCast cast)
		(= theFeatures features)
		(= theAddToPics addToPics)
		(= theMouseDownHandler mouseDownHandler)
		(= theKeyDownHandler keyDownHandler)
		(= theDirectionHandler directionHandler)
		(= theWalkHandler walkHandler)
		(= curRoomObstacles (curRoom obstacles?))
		(curRoom obstacles: ((List new:) add: yourself:))
		((= cast (EventHandler new:)) name: {newCast} add:)
		((= features (EventHandler new:))
			name: {newFeatures}
			add: self
		)
		((= addToPics (EventHandler new:)) name: {newATPs} add:)
		((= mouseDownHandler (EventHandler new:))
			name: {newMH}
			add: self
		)
		((= keyDownHandler (EventHandler new:))
			name: {newKH}
			add: self
		)
		((= directionHandler (EventHandler new:))
			name: {newDH}
			add: self
		)
		((= walkHandler (EventHandler new:)) name: {newWH} add:)
		(if register (inventory hide:) (= register 0))
		(theIconBar disable:)
		(DrawPic 98 dpOPEN_FADEPALETTE)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if local15 (return))
		(cond 
			(
				(and
					(&
						(= temp0
							(OnControl
								4
								((User curEvent?) x?)
								((User curEvent?) y?)
							)
						)
						$0002
					)
					(!= local8 2)
				)
				(= local8 2)
				(SetCursor 190 0 1)
			)
			((and (& temp0 $0004) (!= local8 3)) (= local8 3) (SetCursor 190 0 2))
			((and (& temp0 $0008) (!= local8 4)) (if (!= local8 4) (= local8 4) (SetCursor 190 0 0)))
			((and (& temp0 $4000) (!= local8 1))
				(= local8 1)
				(theGame setCursor: ((theIconBar at: 2) cursor?))
			)
			((and (not (& temp0 $400e)) (!= local8 5))
				(= local8 5)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
		)
	)
	
	(method (dispose)
		(theGame setCursor: waitCursor)
		(super dispose:)
		(cast
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
			dispose:
		)
		(addToPics dispose:)
		(features delete: self dispose:)
		(mouseDownHandler delete: self dispose:)
		(keyDownHandler delete: self dispose:)
		(directionHandler delete: self dispose:)
		(walkHandler delete: self dispose:)
		((curRoom obstacles?) dispose:)
		(curRoom obstacles: curRoomObstacles)
		(= cast theCast)
		(= features theFeatures)
		(= mouseDownHandler theMouseDownHandler)
		(= keyDownHandler theKeyDownHandler)
		(= directionHandler theDirectionHandler)
		(= walkHandler theWalkHandler)
		(= addToPics theAddToPics)
		(ego reset: 2)
		(if (== curRoomNum 781) (ego setPri: 13))
		(DrawPic (curRoom picture?) dpOPEN_NO_TRANSITION)
		(if addToPics (addToPics doit:))
		(theIconBar enable:)
		(theGame handsOn:)
		(switch register
			(1
				(Animate (cast elements?) 1)
				(if
					(not
						(if (== local9 2)
							(not (& ((inventory at: 11) state?) $0008))
						)
					)
					(theGame givePoints: 3)
				)
				(curRoom notify:)
			)
			(4660
				(theGame givePoints: 3)
				(Animate (cast elements?) 1)
				(curRoom setScript: (ScriptID 190 2))
			)
		)
		(if (!= (curRoom script?) makeRainScript)
			(DisposeScript 190)
		)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(switch local9
					(0 1 (= register 12))
					(1 0 (= register 16))
					(2 2 (= register 18))
				)
				(Message msgGET 190 3 0 register 1 @local16)
				(= cycles 1)
			)
			(1
				(switch local9
					(0 1 (= register 13))
					(1 0 (= register 17))
					(2 2 (= register 19))
				)
				(Message msgGET 190 3 0 register 1 @local216)
				(= cycles 1)
			)
			(2
				(switch local9
					(0 1 (= register 14))
					(1 0 (= register 20))
					(2 2 (= register 21))
				)
				(Message msgGET 190 3 0 register 1 @local416)
				(= register 0)
				(= cycles 1)
			)
			(3
				(DrawPic 190 dpOPEN_FADEPALETTE)
				(self cue:)
			)
			(4
				(Display
					@local416
					dsCOORD
					45
					30
					dsWIDTH
					local14
					dsCOLOR
					98
					dsFONT
					1111
				)
				(if (== local9 2) (= temp1 49) else (= temp1 40))
				(Display
					@local16
					dsCOORD
					45
					temp1
					dsWIDTH
					local14
					dsCOLOR
					98
					dsFONT
					1111
				)
				(Display
					{INCANTATION:}
					dsCOORD
					178
					27
					dsWIDTH
					100
					dsCOLOR
					98
					dsFONT
					1111
				)
				(Display
					@local216
					dsCOORD
					178
					37
					dsWIDTH
					100
					dsCOLOR
					98
					dsFONT
					1111
				)
				(User canInput: 1 canControl: 1)
				(= local8 999)
				(= local15 0)
			)
			(5
				(-- state)
				(switch local9
					(0
						(if
							(or
								(!= curRoomNum 230)
								(not (Btst 23))
								(and (Btst 23) (Btst 24))
							)
							(messager say: 4 2 5 0 0 190)
						else
							(= register 1)
							(self dispose:)
						)
					)
					(2
						(= temp0 ((inventory at: 11) state?))
						(cond 
							(
								(and
									(OneOf temp0 15 7)
									(or
										(!= curRoomNum 340)
										(and
											(== curRoomNum 340)
											(not (theCast contains: (ScriptID 344 2)))
										)
									)
								)
								(messager say: 6 2 2 0 0 190)
							)
							(
								(and
									(OneOf temp0 15 7)
									(== curRoomNum 340)
									(theCast contains: (ScriptID 344 2))
								)
								(= register 1)
								(self dispose:)
							)
							(else (messager say: 6 2 11 0 0 190))
						)
					)
					(1
						(cond 
							((and (ego has: 19) (== global161 15)) (messager say: 5 2 23 0 0 190))
							((== global161 7)
								(= global161 (| global161 $0008))
								(= register 4660)
								(Bset 31)
								(self dispose:)
							)
							(else (messager say: 5 2 11 0 0 190))
						)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event modifiers?))
				(or
					(& (event type?) evMOUSEBUTTON)
					(and
						(== (event type?) evKEYBOARD)
						(== (event message?) KEY_RETURN)
					)
				)
				(not (event claimed?))
			)
			(switch local8
				(1
					(messager
						say:
						(switch local9
							(0 4)
							(1 5)
							(2 6)
						) 1 0 0 0 190
					)
				)
				(2
					(if (> local9 0)
						(theGame handsOff:)
						(-- local9)
						(= state -1)
						(self cue:)
					else
						(messager say: 2 5 0 0 0 190)
					)
				)
				(3
					(if (< local9 2)
						(theGame handsOff:)
						(++ local9)
						(= state -1)
						(self cue:)
					else
						(messager say: 1 5 0 0 0 190)
					)
				)
				(4 (self cue:))
				(5 (self dispose:))
			)
			(= local8 -1)
			(event claimed: 1)
		)
		(event claimed?)
	)
)

(instance localSound of Sound
	(properties)
)

(instance makeRainScript of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 190)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 59)
				(messager say: 3 0 9 1 self 0)
			)
			(1
				(KQ6Print
					say: 0 3 0 9 2 0 0 0
					posn: 10 10
					width: 289
					init:
				)
				(ego
					normal: 0
					view: 586
					cel: 0
					setLoop: 0
					cycleSpeed: 5
					setCycle: Fwd
				)
				(= seconds 13)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(localSound number: 945 loop: 1 play:)
				(ego cel: 0 setLoop: 1 setCycle: CT 2 1 self)
			)
			(3 (ego setCycle: CT 0 -1 self))
			(4 (ego setCycle: CT 2 1 self))
			(5 (ego setCycle: CT 0 -1 self))
			(6 (ego setCycle: End self))
			(7
				(messager say: 3 0 9 3 self 0)
			)
			(8
				(Bclr 59)
				(ego reset: egoCel)
				(if (== curRoomNum 781) (ego setPri: 13))
				(localSound stop: dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
