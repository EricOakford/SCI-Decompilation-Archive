;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU) ;MENU = 997
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use Sound)
(use Save)
(use User)

(public
	ToggleSound 0
	;setHighSpeedHero 1
	;EO: The High Speed Hero option was removed sometime between 1.000 and 1.200.
	;Could it be added back in with a patch?
)

; (procedure (setHighSpeedHero enabled)
;	(if (not (Btst HIGH_SPEED_HERO))
;		(if enabled
;			(SetMenu $308 p_value TRUE p_text {Normal Speed Hero})
;			(if (not fastEgo)
;				(ego setStep: (* (ego xStep?) 2) (* (ego yStep?) 2))
;				(= fastEgo TRUE)
;			)
;		else
;			(SetMenu $308 p_value FALSE p_text {High Speed Hero})
;			(if fastEgo
;				(ego
;					setStep: (/ (+ (ego xStep?) 1) 2) (/ (+ (ego yStep?) 1) 2)
;				)
;				(= fastEgo FALSE)
;			)
;		)
;	)
;)


(procedure (ToggleSound)
	(if (GetMenu soundI p_value)
		(DoSound SoundOn FALSE)
		(SetMenu soundI p_value FALSE p_text {Turn on})
	else
		(DoSound SoundOn TRUE)
		(SetMenu soundI p_value TRUE p_text {Turn off})
	)
)

(procedure (menuHandled event)
	(if (> argc 1) (Format (User inputLineAddr?) &rest))
	(event claimed: FALSE type: keyDown message: (User echo?))
)

(instance aWin of SysWindow
	(properties
		back vLCYAN
	)
)

(class TheMenuBar of MenuBar
	
	(method (init)
		(AddMenu { \01_}
			{About Glory I `^g:Help`#1}
		)
		(AddMenu { File_}
			{Save Game`#5:Restore Game`#7:--!:Restart Game`#9:Quit`^q}
		)
		(AddMenu { Game_}
			{Faster Animation`+:Normal Animation`=:Slower Animation`-:--!:Sound Volume...`^v:Turn Off Sound`#2=1}
			;:--!:High Speed Hero`#4=0
		)
		(AddMenu { Action_}
			{Cast Spell`^c:Fight`^f:Escape`^e:--!:Pause Game`^p:Repeat`#3}
		)
		(AddMenu { Information_}
			{Inventory`^I:Char Sheet`^S:--!:Time/Day`^T:Ask about`^a:Look at`^l}
		)
		(SetMenu saveI p_said 'save[/game]')
		(SetMenu restoreI p_said 'restore[/game]')
		(SetMenu restartI p_said 'restart[/game]')
		(SetMenu pauseI p_said 'pause[/game]')
		(SetMenu normalI p_said 'normal[/speed]')
		(SetMenu invI p_said '/inventory')
		(SetMenu timeI p_said 'are<time<what')
	)
	
	(method (handleEvent event &tmp [i 3] newVol [str 300] oldPause)
		(switch (super handleEvent: event)
			(aboutI
				(if (< numColors 8) (aWin color: vBLACK back: vWHITE))
				(Print
					(Format @str MENU 0 version)
					#title {__Quest for Glory I Credits__}
					#mode teJustCenter
					#width 160
					#font smallFont
					#at -1 20
					#window aWin
				)
				(Print
					(Format @str MENU 1)
					#title {And last, but not least...}
					#mode teJustCenter
					#width 180
					#font smallFont
					#at -1 20
					#window aWin
				)
			)
			(helpI
				(Print MENU 2 #font smallFont)
				;      DURING THE GAME:
				; Click at the top of the screen or press ESC to use the menus. Additional shortcuts are shown there.
				; Click Right or Shift-Click Left Mouse
				; Button on an object to look at it.
				;
				;     IN TYPING WINDOWS:
				; Arrows, Home and End move the cursor, or click anywhere with the mouse. Ctrl-C clears the line.
				;
				;     IN DIALOG WINDOWS:
				; Select the outlined item with <Enter> and
				; use Tab and Shift-Tab to move between 
				; choices. 
				; Or click with the mouse to select an item.
				;
				; ESC always cancels. 
			)
			(saveI
				(if
					(Btst fSaveAllowed)
					(theGame save:)
				)
			)
			(restoreI
				(theGame restore:)
			)
			(restartI
				(if
					(Print MENU 3
						#button {Restart} TRUE
						#button {Continue} FALSE
						#icon vIcons 1 3
					)
					; You can change your mind
					;   if you have one.
					(theGame restart:)
				)
			)
			(quitI
				(PromptQuit)
			)
			(fasterI
				(if (> speed 0)
					(theGame setSpeed: (-- speed))
				)
			)
			(normalI
				(theGame setSpeed: 6)
			)
			(slowerI
				(if (< speed 16)
					(theGame setSpeed: (++ speed))
				)
			)
			(volumeI
				(if
					(!=
						(= newVol
							(GetNumber {Volume (1 - 16)?} (+ 1 (DoSound ChangeVolume)))
						)
						-1
					)
					(if (< (-- newVol) 0) (= newVol 0))
					(if (> newVol 15) (= newVol 15))
					(DoSound ChangeVolume newVol)
				)
			)
			(soundI
				(ToggleSound)
			)
			; (highSpeedHeroI
			;	(setHighSpeedHero (not fastEgo)
			;	)
			(castI
				(menuHandled event {cast_})
			)
			(fightI
				(menuHandled event {fight_})
			)
			(escapeI
				(menuHandled event {escape_})
			)
			(pauseI
				(= oldPause (Sound pause: TRUE))
				(Print
					MENU 4
					#title {Game Paused}
					#icon vIcons 0 0
					#button {Let's be a hero} TRUE
				)
				(Sound pause: oldPause)
				; Every hero needs a break now and then.  Have a nice nap.
			)
			(repeatI
				(menuHandled event)
			)
			(invI
				(cond 
					((not (HaveMem 2000))
						(HighPrint MENU 5)
					)
					((not isHandsOff)
						((ScriptID INVSHEET 0) init: doit:)
					)
				)
			)
			(charI
				(cond 
					(
						(or (not (HaveMem 2000)) (== curRoomNum 95))
						(HighPrint MENU 5)
					)
					((not isHandsOff)
						((ScriptID CHARSHEET 0) useWindow: TRUE showBars: TRUE init: doit:)
					)
				)
			)
			(timeI
				(ShowTime)
			)
			(askI
				(menuHandled event {ask about_})
			)
			(lookI
				(menuHandled event {look at_})
			)
		)
	)
)
