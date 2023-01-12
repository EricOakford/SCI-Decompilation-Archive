;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include game.sh)
(use Main)
(use Intrface)
(use DScript)
(use Osc)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm34 0
)

(local
	local0
	local1
	local2
)
(instance rm34 of Room
	(properties
		picture 99
	)
	
	(method (init &tmp temp0)
		(LoadMany SOUND 422 431 419)
		(LoadMany VIEW 24)
		(HandsOff)
		(theMusic fade: loop: 0)
		(holodude init:)
		(super init:)
		(ego init: x: 180 y: 225)
		(NormalEgo 3 1 61)
		(self setScript: startUp)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 34 0)
			)
			(verbDo
				(Print 34 1)
			)
			(verbTalk
				(Print 34 2)
			)
			(verbSmell
				(Print 34 3)
			)
			(verbTaste
				(Print 34 4)
			)
			(verbUse
				(switch theItem
					(iOratPart
						(ego setScript: egoDropOratPart)
					)
					(iDehydratedWater
						(Print 34 5)
					)
					(else
						(Print 34 6)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance startUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(ego setMotion: MoveTo 180 175 self)
			)
			(2 (= cycles 9))
			(3
				(if (not (Btst fSeenHologram))
					(Print 34 7 #at 10 80 #width 100)
					(Print 34 8 #at 10 80 #width 100)
				)
				(= cycles 6)
			)
			(4
				(theMusic
					number:
						(cond 
							((and (ego has: iOratPart) (Btst fGadgetOn)) 434)
							((and (Btst fGadgetOn) (not (ego has: iOratPart))) 422)
							(else 431)
						)
					loop: -1
					play:
				)
				(curRoom overlay: 434 100)
				(= cycles 20)
			)
			(5
				(curRoom overlay: 334 100)
				(= cycles 20)
			)
			(6
				(curRoom overlay: 234 100)
				(= cycles 20)
			)
			(7
				(curRoom overlay: 134 PLAIN)
				(= cycles 20)
			)
			(8
				(curRoom drawPic: 34)
				(holoMouth init:)
				(= cycles 20)
			)
			(9
				(UnLoad PICTURE 434)
				(UnLoad PICTURE 334)
				(UnLoad PICTURE 234)
				(UnLoad PICTURE 134)
				(holoEyes init: setCycle: Forward)
				(cond 
					((Btst fOratPartKnown)
						(if (Btst fGadgetOn)
							(self setScript: translatorOn2 self)
						else
							(self setScript: translatorOff2 self)
						)
					)
					((Btst fGadgetOn) (self setScript: translatorOn1 self))
					(else (self setScript: translatorOff1 self))
				)
			)
			(10 0)
		)
	)
)

(instance translatorOn1 of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 34 9 #at 10 80 #width 100)
				(= cycles 3)
			)
			(1
				(self
					save1:
						(Display 34 10
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 8)
			)
			(2
				(self restore:)
				(= seconds 2)
			)
			(3
				(self
					save1:
						(Display 34 11
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 8)
			)
			(4
				(self restore:)
				(= seconds 2)
			)
			(5 (Print 34 12) (= cycles 2))
			(6
				(self
					save1:
						(Display 34 13
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 6)
			)
			(7
				(self restore:)
				(= seconds 2)
			)
			(8
				(self
					save1:
						(Display 34 14
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 6)
			)
			(9
				(self restore:)
				(= seconds 2)
			)
			(10
				(self
					save1:
						(Display 34 15
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 13)
			)
			(11
				(self restore:)
				(= seconds 2)
			)
			(12
				(self
					save1:
						(Display 34 16
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 6)
			)
			(13
				(self restore:)
				(Bset (= cycles 6))
				(Bset fOratPartKnown)
			)
			(14
				(if (ego has: iOratPart)
					(= seconds 10)
					(HandsOn)
					(User canControl: FALSE)
					(theIconBar disable: ICON_WALK)
				else
					(Print 34 17 #at 10 80 #width 100)
					(curRoom newRoom: 22)
					(self dispose:)
				)
			)
			(15
				(if (= local2 0)
					(Print 34 18 #at 10 80 #width 100)
					(curRoom newRoom: 22)
				)
				(self dispose:)
			)
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local1 0)
	)
)

(instance translatorOff1 of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 34 19 #at 10 80 #width 100)
				(= cycles 3)
			)
			(1
				(self
					save1:
						(Display 34 20
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 4)
			)
			(2
				(self restore:)
				(= seconds 2)
			)
			(3
				(self
					save1:
						(Display 34 21
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 5)
			)
			(4
				(self restore:)
				(= seconds 2)
			)
			(5
				(self
					save1:
						(Display 34 22
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 5)
			)
			(6
				(self restore:)
				(= seconds 2)
			)
			(7
				(self
					save1:
						(Display 34 23
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 5)
			)
			(8
				(self restore:)
				(= seconds 2)
			)
			(9
				(Print 34 24 #at 10 80 #width 100)
				(= cycles 3)
			)
			(10
				(curRoom newRoom: 22)
				(Bset fSeenHologram)
				(self dispose:)
			)
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local1 0)
	)
)

(instance translatorOn2 of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 34 25 #at 10 80 #width 100)
				(= cycles 3)
			)
			(1
				(self
					save1:
						(Display 34 26
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 3)
			)
			(2
				(if (ego has: iOratPart)
					(= seconds 6)
					(HandsOn)
					(User canControl: FALSE)
					(theIconBar disable: ICON_WALK)
				else
					(Print 34 27 #at 10 80 #width 100)
					(curRoom newRoom: 22)
					(self dispose:)
				)
			)
			(3
				(self restore:)
				(= cycles 6)
			)
			(4
				(if (= local2 0)
					(Print 34 28 #at 10 80 #width 100)
					(curRoom newRoom: 22)
				)
			)
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local1 0)
	)
)

(instance translatorOff2 of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Print 34 25) (= cycles 3))
			(1
				(self
					save1:
						(Display 34 29
							p_mode teJustCenter
							p_font SYSFONT
							p_width 150
							p_at 10 40
							p_color myTextColor7
							p_save
						)
				)
				(= seconds 3)
			)
			(2
				(self restore:)
				(= cycles 6)
			)
			(3
				(Print 34 30)
				(curRoom newRoom: 22)
			)
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local1 0)
	)
)

(instance egoDropOratPart of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 1)
				(= cycles 3)
				(= local0 1)
			)
			(1
				(ego view: 24 cel: 0 cycleSpeed: 10 setCycle: CycleTo 4 1 self)
			)
			(2
				(soundFx number: 419 loop: 0 play:)
				(ego put: iOratPart 34 setCycle: EndLoop self)
			)
			(3
				(Print 34 31 #at 10 80 #width 100)
				;EO: This did not decompile correctly.
				(NormalEgo 1 1 61)
				(oratPart init: posn: (ego x?) (ego y?))
				(= cycles 3)
				
			)
			(4
				(Print 34 32)
				(= cycles 3)
			)
			(5
				(soundFx number: 433 loop: 1 play:)
				(door init: posn: 82 106 cycleSpeed: 8 setCycle: EndLoop self)
			)
			(6
				(soundFx stop:)
				(ego setPri: 10 setMotion: MoveTo 88 112 self)
				(theMusic fade:)
			)
			(7 (curRoom newRoom: 35))
		)
	)
)

(instance holoMouth of Prop
	(properties
		x 216
		y 123
		view 134
		loop 1
		priority 8
		signal fixPriOn
		cycleSpeed 6
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== local1 0)
				(startUp script?)
				((startUp script?) save1?)
				(not cycler)
			)
			(self setCycle: Oscillate 3)
			(= local1 1)
		)
	)
	
	(method (doVerb theVerb theItem)
		(holodude doVerb: theVerb theItem)
	)
)

(instance holoEyes of Prop
	(properties
		x 216
		y 71
		view 134
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Oscillate)
	)
	
	(method (doit)
		(super doit:)
		(if (holoMouth cel?) (self show:) else (self hide:))
	)
	
	(method (doVerb theVerb theItem)
		(holodude doVerb: theVerb theItem)
	)
)

(instance head of Prop
	(properties
		x 218
		y 146
		view 61
		loop 8
		cycleSpeed 6
	)
	
	(method (doVerb theVerb theItem)
		(holodude doVerb: theVerb theItem)
	)
)

(instance holodude of Feature
	(properties
		x 219
		y 55
		description {holodude}
		sightAngle 45
		onMeCheck $0400
		lookStr {holodude look string}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 34 33)
			)
			(verbDo
				(Print 34 34)
			)
			(verbSmell
				(Print 34 35)
			)
			(verbTaste
				(Print 34 36)
			)
			(verbTalk
				(Print 34 37)
			)
			(verbUse
				(switch theItem
					(iOratPart
						(ego setScript: egoDropOratPart)
					)
					(iBuckazoid
						(Print 34 38)
					)
					(iCartridge
						(Print 34 39)
					)
					(iWidget
						(Print 34 40)
					)
					(iBrokenGlass
						(Print 34 41)
					)
					(iDehydratedWater
						(Print 34 5)
					)
					(iKnife
						(Print 34 42)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door of Prop
	(properties
		view 234
		signal (| ignrAct fixedLoop)
		cycleSpeed 10
	)
)

(instance oratPart of View
	(properties
		view 134
		loop 2
		signal ignrAct
	)
)
