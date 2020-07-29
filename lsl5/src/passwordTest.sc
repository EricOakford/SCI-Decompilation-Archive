;;; Sierra Script 1.0 - (do not remove this comment)
(script# 155)
(include game.sh)
(use Main)
(use Intrface)
(use File)
(use Game)

(public
	passwordTest 0
)

(local
	[newPassword 10]
	[savedPassword 10]
	attempts
)
(procedure (PasswordPrompt &tmp ret)
	(if (not (StrCmp @savedPassword {NONE}))
		(= ret
			(Print
				{If you would like to use a\npassword to protect this game,\nselect "Password" below.}
				#new
				#button {Why bother?} 1
				#button {Password} 2
				#title {An ounce of prevention...}
				#width 160
				#at 3 -1
			)
		)
	else
		(++ attempts)
		(= ret
			(Print
				{Please enter your password,\nexactly as you typed it:}
				#new
				#edit @newPassword 20
				#new
				#button {Ok} 1
				#button {Change} 2
				#title {Hey! Is that you again?}
				#width 160
				#at 3 -1
			)
		)
	)
	(localproc_01f3)
	(return ret)
)

(procedure (localproc_01f3 &tmp i temp1)
	(= i 0)
	(while (< i (StrLen @newPassword))
		(if (> (= temp1 (StrAt @newPassword i)) 90)
			(StrAt @newPassword i (- temp1 32))
		)
		(++ i)
	)
)

(procedure (localproc_0231 &tmp i temp1 temp2)
	(= i 0)
	(while (< i (StrLen @savedPassword))
		(= temp2 (- 167 i))
		(= temp1 (^ (StrAt @savedPassword i) temp2))
		(StrAt @savedPassword i temp1)
		(++ i)
	)
)

(procedure (localproc_0270 &tmp [str 10])
	(if (!= (memFile open: 1) 0)
		(memFile readString: @savedPassword 20 read: @str 8 close:)
		(= pokerJackpot (ReadNumber @str))
	else
		(StrCpy @savedPassword {NONE})
		(= pokerJackpot 1000)
		(localproc_02c9)
	)
	(localproc_0231)
)

(procedure (localproc_02c9 &tmp [str 10])
	(if (!= (memFile open: 2) 0)
		(localproc_0231)
		(Format @str 155 3 pokerJackpot)
		(memFile
			writeString: @savedPassword
			writeString: {\n}
			writeString: @str
			close:
		)
	)
)

(instance passwordTest of Room
	(properties
		picture 110
		style IRISOUT
	)
	
	(method (init &tmp temp0)
		(super init:)
		(localproc_0270)
		(if (StrCmp @savedPassword {NONE})
			(= newPassword 0)
		else
			(StrCpy @newPassword @savedPassword)
		)
		(while
			(and
				(< attempts 5)
				(or
					(not (= temp0 (PasswordPrompt)))
					(StrCmp @savedPassword @newPassword)
				)
			)
		)
		(cond 
			((== attempts 5)
				(Print 155 0 #title {Five Strikes and You're Out!})
				(= quit TRUE)
				(return)
			)
			((== temp0 2)
				(GetInput @newPassword 20 {Please enter your new password:})
				(localproc_01f3)
				(StrCpy @savedPassword @newPassword)
				(localproc_02c9)
				(Printf 155 1 @newPassword 80 {Write this down!})
			)
		)
		(DrawPic 111)
		(if
			(Print 155 2
				#button {Restore} 1
				#button {Continue} 0
				#title {Welcome back!}
				#width 160
				#at 3 -1
			)
			(theGame restore:)
		)
		(DisposeScript FILE)
		(curRoom newRoom: 160)
	)
)

(instance memFile of File
	(properties
		name "MEMORY.DRV"
	)
)
